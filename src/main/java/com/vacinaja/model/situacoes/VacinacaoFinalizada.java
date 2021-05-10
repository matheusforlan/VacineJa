package com.vacinaja.model.situacoes;


import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Vacina;

public class VacinacaoFinalizada extends Situacao{

    @Override
    public String mudaSituacao(Cidadao cidadao) {
        return "O cidadão de cpf " + cidadao.getCpf() + " já finalizou seu processo de vacinação";
    }

    @Override
    public String tomaVacina(Cidadao cidadao, Vacina vacina) {
        return "O cidadão de cpf " + cidadao.getCpf() + " já finalizou seu processo de vacinação";
    }

    @Override
    public String toString(){
        return "VACINAÇÃO FINALIZADA";
    }

}
