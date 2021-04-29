package com.vacinaja.service;

import java.util.List;
import java.util.Optional;

import com.vacinaja.DTO.VacinaDTO;
import com.vacinaja.model.Vacina;

public interface VacinaService {

    public Optional<Vacina> getVacinaById(Long id);

    public void removerVacina(Vacina vacina);

    public void salvarVacina(Vacina vacina);

    public List<Vacina> listarVacinas();

    public Vacina cadastrarVacina(VacinaDTO vacinaDTO);  
    
}
