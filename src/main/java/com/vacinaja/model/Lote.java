package com.vacinaja.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Vacina vacina;

    private int quantidadeDoses;

    private Date dataValidade;

    Lote() {}

    public Lote(Vacina vacina, int quantidadeDoses, Date dataValidade) {
        this.vacina = vacina;
        this.quantidadeDoses = quantidadeDoses;
        this.dataValidade = dataValidade;
    }

    public long getId() {
        return this.id;
    }

    public Vacina getVacina() {
        return this.vacina;
    }

    public int getQuantidadeDoses() {
        return this.quantidadeDoses;
    }

    public void setQuantidadeDoses(int quantidadeDoses) {
        this.quantidadeDoses = quantidadeDoses;
    }

    public Date getDataValidade() {
        return this.dataValidade;
    }

    @Override
    public String toString() {
        return "Lote - " + this.id + "\n" +
        "{Vacina - " + this.vacina.getNome() + "\n" +
        " Quantidade de doses do lote - " + this.quantidadeDoses + "\n" +
        " Data de validade - " + this.dataValidade.toString() + "}\n";

    }
}
