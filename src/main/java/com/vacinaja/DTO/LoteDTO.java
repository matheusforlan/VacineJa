package com.vacinaja.DTO;

import java.sql.Date;

public class LoteDTO {

    private long id;

    private long vacinaId;

    private int quantidadeDoses;

    private Date dataValidade;

    public LoteDTO(long vacinaId, int quantidadeDoses, Date dataValidade) {
        this.vacinaId = vacinaId;
        this.quantidadeDoses = quantidadeDoses;
        this.dataValidade = dataValidade;
    }

    public long getId() {
        return this.id;
    }

    public long getVacinaId() {
        return this.vacinaId;
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
