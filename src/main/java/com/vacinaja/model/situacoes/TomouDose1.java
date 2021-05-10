package com.vacinaja.model.situacoes;


import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Vacina;

public class TomouDose1 extends Situacao{

    @Override
    public String mudaSituacao(Cidadao cidadao) {
        EnumSituacoes situacao = EnumSituacoes.TOMOU_DOSE_1;
        cidadao.setSituacao(situacao);
        return "O cidadão de cpf "+ cidadao.getCpf() + " se encontra agora habilitado para a dose 2";
    }

    @Override
    public String tomaVacina(Cidadao cidadao, Vacina vacina) {
        return "O cidadão de cpf " + cidadao.getCpf() + " não está habilitado para a dose 2";
    }

    @Override
    public String toString(){
        return "ESPERANDO DOSE 2";
    }

}
