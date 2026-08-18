package com.projeto.fila.service;

import com.projeto.fila.model.Fila;
import com.projeto.fila.model.Medicamento;
import com.projeto.fila.model.Paciente;
import com.projeto.fila.repository.FilaRepository;
import com.projeto.fila.repository.MedicamentoRepository;
import com.projeto.fila.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// Singleton
// Facade
@Service
public class FilaFacade {

    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    
    @Autowired
    private FilaRepository filaRepository;

    public Fila entrarFila(Paciente paciente, Long medicamentoId, String tipoNotificacao) {
        
        // Salvar paciente
        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        
        // Buscar remédio
        Medicamento medicamento = medicamentoRepository.findById(medicamentoId).orElse(null);
        if (medicamento == null) {
            throw new RuntimeException("Medicamento não encontrado!");
        }
        
        // Criar a fila
        Fila novaFila = new Fila();
        novaFila.setPaciente(pacienteSalvo);
        novaFila.setMedicamento(medicamento);
        novaFila.setDataEntrada(LocalDateTime.now());
        novaFila.setTipoNotificacao(tipoNotificacao);
        
        // Verificar Estoque e usar o Strategy
        if (medicamento.getQuantidadeEstoque() > 0) {
            novaFila.setStatus("FINALIZADO");
            
            // Strategy
            NotificacaoStrategy strategy = escolherStrategy(tipoNotificacao);
            if (strategy != null) {
                strategy.enviar(pacienteSalvo, "O medicamento " + medicamento.getNome() + " está disponível!");
            }
            
            // Diminuir o estoque
            medicamento.setQuantidadeEstoque(medicamento.getQuantidadeEstoque() - 1);
            medicamentoRepository.save(medicamento);
        } else {
            novaFila.setStatus("AGUARDANDO");
            System.out.println("Sem estoque. Paciente na fila de espera.");
        }
        
        return filaRepository.save(novaFila);
    }
    
    public void notificarChegada(Long filaId) {
        Fila fila = filaRepository.findById(filaId).orElse(null);
        if (fila != null && fila.getStatus().equals("AGUARDANDO")) {
            
            //Strategy
            NotificacaoStrategy strategy = escolherStrategy(fila.getTipoNotificacao());
            if (strategy != null) {
                strategy.enviar(fila.getPaciente(), "Seu remédio chegou!");
            }
            
            fila.setStatus("NOTIFICADO");
            filaRepository.save(fila);
        }
    }

    private NotificacaoStrategy escolherStrategy(String tipo) {
        if (tipo.equalsIgnoreCase("SMS")) {
            return new SmsNotificacao();
        } else if (tipo.equalsIgnoreCase("E-mail")) {
            return new EmailNotificacao();
        }
        return null;
    }
}
