package com.vacinaja.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String fabricante;

    private BigInteger diasParaSegundaDose;

    Vacina(){}

    public Vacina(String nome, String fabricante, BigInteger diasParaSegundaDose) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.diasParaSegundaDose = diasParaSegundaDose;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return this.fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public BigInteger getDiasParaSegundaDose() {
        return this.diasParaSegundaDose;
    }

    public void setDiasParaSegundaDose(BigInteger diasParaSegundaDose) {
        this.diasParaSegundaDose = diasParaSegundaDose;
    }

    public String toString() {
        return this.id + " - " + this.nome + " - " + this.fabricante;
    }
    
}
