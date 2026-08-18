package com.projeto.fila.service;

import com.projeto.fila.model.Paciente;

// Strategy
public interface NotificacaoStrategy {
    void enviar(Paciente paciente, String mensagem);
}
