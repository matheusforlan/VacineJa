package com.vacinaja.service;

import java.util.Optional;

import com.vacinaja.DTO.CidadaoDTO;
import com.vacinaja.model.Cidadao;

public interface CidadaoService {

    public Optional<Cidadao> getCidadaoByCpf(String cpf);

    public void removerCidadao(Cidadao cidadao);

    public void salvarCidadao(Cidadao cidadao);

    public void cadastrarCidadao(CidadaoDTO cidadaoDTO);
    
}
