package com.vacinaja.DTO;

import java.sql.Date;

public class AplicacaoVacinaDTO {
    public String cpfCidadao;

    public long idVacina;

    public Date dataAplicacao;


    public String getCpfCidadao() {
        return this.cpfCidadao;
    }

    public void setCpfCidadao(String cpfCidadao) {
        this.cpfCidadao = cpfCidadao;
    }

    public long getIdVacina() {
        return this.idVacina;
    }

    public void setIdVacina(long idVacina) {
        this.idVacina = idVacina;
    }

    public Date getDataAplicacao() {
        return this.dataAplicacao;
    }

    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

}
