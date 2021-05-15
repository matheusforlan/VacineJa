package com.vacinaja.DTO;

public class AgendamentoVacinacaoDTO {

    private String cpfCidadao;

    private String data;

    private String horario;

    public AgendamentoVacinacaoDTO(String cpfCidadao, String data, String horario) {
        this.cpfCidadao = cpfCidadao;
        this.data = data;
        this.horario = horario;
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
