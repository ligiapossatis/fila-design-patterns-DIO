package com.projeto.fila.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Fila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Paciente paciente;
    
    @ManyToOne
    private Medicamento medicamento;
    
    private LocalDateTime dataEntrada;
    private String status; // AGUARDANDO
    private String tipoNotificacao; // SMS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medicamento getMedicamento() { return medicamento; }
    public void setMedicamento(Medicamento medicamento) { this.medicamento = medicamento; }
    public LocalDateTime getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(LocalDateTime dataEntrada) { this.dataEntrada = dataEntrada; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTipoNotificacao() { return tipoNotificacao; }
    public void setTipoNotificacao(String tipoNotificacao) { this.tipoNotificacao = tipoNotificacao; }
}
