package com.vacinaja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class AgendamentoVacinacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Cidadao cidadao;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private String data;

    @DateTimeFormat(pattern = "00:00")
    private String horario;

    AgendamentoVacinacao() {}

    public AgendamentoVacinacao(Cidadao cidadao, String data, String horario) {
        this.cidadao = cidadao;
        this.data = data;
        this.horario = horario;
    }

    public long getId() {
        return this.id;
    }

    public Cidadao getCidadao() {
        return this.cidadao;
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
