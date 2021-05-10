package com.vacinaja.model.situacoes;


import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Vacina;


public class NaoHabilitado extends Situacao{

    @Override
    public String mudaSituacao(Cidadao cidadao) {
        EnumSituacoes situacao = EnumSituacoes.HABILITADO_DOSE_1;
        cidadao.setSituacao(situacao);
        
        return "Cidadão de cpf " + cidadao.getCpf() + " está agora habilitado para a dose 1 da vacina."; 
    }

    @Override
    public String tomaVacina(Cidadao cidadao, Vacina vacina) {
        return "Cidadão de cpf " + cidadao.getCpf() + "não esta hábilitado para a vacinação";
        
    }

    @Override
    public String toString(){
        return "NÃO HABILITADO";
    }
    
}
