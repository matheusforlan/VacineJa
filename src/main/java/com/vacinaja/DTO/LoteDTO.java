package com.vacinaja.DTO;

import com.vacinaja.model.Vacina;
import java.sql.Date;

public class LoteDTO {

    private long id;

    private Vacina vacina;

    private int quantidadeDoses;

    private Date dataValidade;

    public LoteDTO(Vacina vacina, int quantidadeDoses, Date dataValidade) {
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
    
}
