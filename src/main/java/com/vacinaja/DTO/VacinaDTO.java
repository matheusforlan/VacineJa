package com.vacinaja.DTO;

import java.math.BigInteger;

public class VacinaDTO {

    private Long id;

    private String nome;

    private String fabricante;

    private BigInteger diasParaSegundaDose;

    public VacinaDTO(String nome, String fabricante, BigInteger diasParaSegundaDose) {
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
    
}
