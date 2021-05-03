package com.vacinaja.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AplicacaoVacina {
    
    @Id
    String cpfCidadao;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   
    @MapsId
    private Cidadao cidadao;

    @OneToOne( cascade = CascadeType.ALL)  
    private Vacina vacina;

    private Date dataAplicacao;

    private int dose;

    AplicacaoVacina(){}

    public AplicacaoVacina(Cidadao cidadao, Vacina vacina, Date dataAplicacao){
        this.cidadao = cidadao;
        this.vacina = vacina;
        this.dataAplicacao = dataAplicacao;
        this.dose = 1;
    }

    public int getDose(){
        return this.dose;
    }

    public Cidadao getCidadao(){
        return this.cidadao;
    }
    public Vacina getVacina(){
        return this.vacina;
    }

    public void tomouDose2(){
        this.dose = 2;
    }

    public Date getDataAplicacao(){
        return this.dataAplicacao;
    }




}
