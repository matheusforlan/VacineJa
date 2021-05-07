package com.vacinaja.DTO;

import com.vacinaja.model.Cidadao;

public class AgendamentoVacinacaoDTO {

    private long id;

    private Cidadao cidadao;

    private String data;

    private String horario;

    public AgendamentoVacinacaoDTO(Cidadao cidadao, String data, String horario) {
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
