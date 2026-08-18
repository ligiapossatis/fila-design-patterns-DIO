package com.projeto.fila.service;

import com.projeto.fila.model.Paciente;

// Strategy
public class EmailNotificacao implements NotificacaoStrategy {
    @Override
    public void enviar(Paciente paciente, String mensagem) {
        System.out.println("Enviando E-mail para: " + paciente.getEmail());
        System.out.println("Mensagem: " + mensagem);
    }
}
