package com.vacinaja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class AgendamentoVacinacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String cpfCidadao;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private String data;

    @DateTimeFormat(pattern = "00:00")
    private String horario;

    AgendamentoVacinacao() {}

    public AgendamentoVacinacao(String cpfCidadao, String data, String horario) {
        this.cpfCidadao = cpfCidadao;
        this.data = data;
        this.horario = horario;
    }

    public long getId() {
        return this.id;
    }

    public String getCidadao() {
        return this.cpfCidadao;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
}
