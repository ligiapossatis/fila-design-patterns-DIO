package com.projeto.fila.service;

import com.projeto.fila.model.Paciente;

//Strategy
public class SmsNotificacao implements NotificacaoStrategy {
    @Override
    public void enviar(Paciente paciente, String mensagem) {
        System.out.println("Enviando SMS para o telefone: " + paciente.getTelefone());
        System.out.println("Mensagem: " + mensagem);
    }
}
