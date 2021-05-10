package com.vacinaja.model.situacoes;



import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Vacina;


public class HabilitadoDose1 extends Situacao{

    @Override
    public String mudaSituacao(Cidadao cidadao) {
        return "O cidadão de cpf "+ cidadao.getCpf() +" não pode passar para a próxima fase sem ter tomado a dose 1 da vacina.";
        
    }

    @Override
    public String tomaVacina(Cidadao cidadao, Vacina vacina) {
        if(vacina.getQuantidadeDoses() == 2){
            EnumSituacoes situacao = EnumSituacoes.TOMOU_DOSE_1;
            cidadao.setSituacao(situacao);
        }else{
            EnumSituacoes situacao = EnumSituacoes.VACINACAO_FINALIZADA;
            cidadao.setSituacao(situacao);
        }
        
        return "Cidadão de cpf "+ cidadao.getCpf() + " tomou a dose 1 da vacina.";
    }

    @Override
    public String toString(){
        return "HABILITADO PARA DOSE 1";
    }
}
