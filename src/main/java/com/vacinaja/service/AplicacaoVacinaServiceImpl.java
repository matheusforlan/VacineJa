package com.vacinaja.service;

import java.util.Optional;

import com.vacinaja.model.AplicacaoVacina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinaja.repository.AplicacaoVacinaRespository;

@Service
public class AplicacaoVacinaServiceImpl implements AplicacaoVacinaService{
    @Autowired
    AplicacaoVacinaRespository aplicacaoVacinaRepository;

    @Override
    public void salvar(AplicacaoVacina aplicacaoVacina) {
        aplicacaoVacinaRepository.save(aplicacaoVacina);
        
    }

    @Override
    public Optional<AplicacaoVacina> getById(String cpf) {
        return aplicacaoVacinaRepository.getByCpfCidadao(cpf);
    }
    
}
