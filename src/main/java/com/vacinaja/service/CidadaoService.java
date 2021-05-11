package com.vacinaja.service;

import com.vacinaja.DTO.CidadaoDTO;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.situacoes.EnumSituacoes;

import java.util.List;
import java.util.Optional;


public interface CidadaoService {

    public Optional<Cidadao> getCidadaoByCpf(String cpf);

    public void removerCidadao(Cidadao cidadao);

    public void salvarCidadao(Cidadao cidadao);

    public void cadastrarCidadao(CidadaoDTO cidadaoDTO);

    public void atualizarCidadao(CidadaoDTO cidadaoDTO, Cidadao cidadao);

    public List<Cidadao> getCidadaosBySituacao(EnumSituacoes situacao);
    
}
