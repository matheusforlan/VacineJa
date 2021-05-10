package com.vacinaja.model.situacoes;

public enum EnumSituacoes {
    NAO_HABILITADO(new NaoHabilitado()),
    HABILITADO_DOSE_1(new HabilitadoDose1()),
    TOMOU_DOSE_1(new TomouDose1()),
    HABILITADO_DOSE_2(new HabilitadoDose2()),
    VACINACAO_FINALIZADA(new VacinacaoFinalizada());

    private Situacao situacao;
    private EnumSituacoes(Situacao situacao){
        this.situacao = situacao;
    }
    public Situacao getSituacao() {
        return situacao;
    }
}
