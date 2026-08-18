package com.projeto.fila.controller;

import com.projeto.fila.model.Fila;
import com.projeto.fila.model.Medicamento;
import com.projeto.fila.model.Paciente;
import com.projeto.fila.repository.MedicamentoRepository;
import com.projeto.fila.service.FilaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FilaController {


    private FilaFacade filaFacade;
    
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @PostMapping("/cadastrar-medicamento")
    public Medicamento cadastrarMedicamento(@RequestBody Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @PostMapping("/entrar-fila/{medicamentoId}")
    public Fila entrarFila(@PathVariable Long medicamentoId, @RequestBody Paciente paciente, @RequestParam String tipoNotificacao) {
        return filaFacade.entrarFila(paciente, medicamentoId, tipoNotificacao);
    }

    @PostMapping("/chegou-medicamento/{filaId}")
    public void notificarChegada(@PathVariable Long filaId) {

        filaFacade.notificarChegada(filaId);
    }
}
