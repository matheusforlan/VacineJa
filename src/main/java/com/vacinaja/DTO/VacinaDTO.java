package com.vacinaja.DTO;

public class VacinaDTO {

    private Long id;

    private String nome;

    private String fabricante;

    private int diasParaSegundaDose;

    private int quantidadeDoses;

    public VacinaDTO(String nome, String fabricante, int diasParaSegundaDose, int quantidadeDoses) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.diasParaSegundaDose = diasParaSegundaDose;
        this.quantidadeDoses = quantidadeDoses;
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
    public int getQuantidadeDoses(){
        return this.quantidadeDoses;
    }
    
}
