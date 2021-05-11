package com.vacinaja.model;

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

    private int diasParaSegundaDose;

    private int quantidadeDoses;

    private boolean isDisponivel;

    Vacina(){}

    public Vacina(String nome, String fabricante, int diasParaSegundaDose, int quantidadeDoses) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.diasParaSegundaDose = diasParaSegundaDose;
        this.quantidadeDoses = quantidadeDoses;
        this.isDisponivel = false;
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

    public int getDiasParaSegundaDose() {
        return this.diasParaSegundaDose;
    }

    public void setDiasParaSegundaDose(int diasParaSegundaDose) {
        this.diasParaSegundaDose = diasParaSegundaDose;
    }

    public int getQuantidadeDoses() {
        return this.quantidadeDoses;
    }

    public boolean isDisponivel() {
        return this.isDisponivel;
    }

    public void tornaDisponivel() {
        this.isDisponivel = true;
    }

    public void tornaIndisponivel() {
        this.isDisponivel = false;
    }

    @Override
    public String toString() {
        return "Vacina " + this.id + " - " + this.nome + "\n" +
        "Fabricante - " + this.fabricante + "\n" +
        "Quantidade de Doses - " + this.quantidadeDoses;
    }

   
    
}
