package com.vacinaja.DTO;

public class AgendamentoVacinacaoDTO {

    private long id;

    private String cpfCidadao;

    private String data;

    private String horario;

    public AgendamentoVacinacaoDTO(String cpfCidadao, String data, String horario) {
        this.cpfCidadao = cpfCidadao;
        this.data = data;
        this.horario = horario;
    }

    public long getId() {
        return this.id;
    }

    public String getCpfCidadao() {
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
