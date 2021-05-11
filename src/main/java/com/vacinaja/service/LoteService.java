package com.vacinaja.service;

import com.vacinaja.DTO.LoteDTO;
import com.vacinaja.model.Lote;
import java.util.List;
import java.util.Optional;

public interface LoteService {

    public Optional<Lote> getLoteById(Long id);

    public void removerLote(Lote lote);

    public void salvarLote(Lote lote);

    public List<Lote> listarLotes();

    public Lote cadastrarLote(LoteDTO loteDTO);

    public boolean isVacinaDisponivel(long vacinaId);

    public void tomarDose(long vacinaId);
    
}
