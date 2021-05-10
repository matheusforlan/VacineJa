package com.vacinaja.model.situacoes;


import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Vacina;

public abstract class Situacao {
    Situacao(){};
    public abstract String mudaSituacao(Cidadao cidadao);
    public abstract String tomaVacina(Cidadao cidadao, Vacina vacina);
}
