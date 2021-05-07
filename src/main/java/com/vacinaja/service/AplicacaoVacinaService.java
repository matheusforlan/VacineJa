package com.vacinaja.service;

import java.util.Optional;

import com.vacinaja.model.AplicacaoVacina;

public interface AplicacaoVacinaService {
    public void salvar(AplicacaoVacina aplicacaoVacina);
    public Optional<AplicacaoVacina> getById(String cpf);
}
