package com.vacinaja.service;

import java.util.Optional;

import com.vacinaja.DTO.CidadaoDTO;
import com.vacinaja.model.Cidadao;

import com.vacinaja.repository.CidadaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadaoServiceImpl implements CidadaoService{

    @Autowired
    CidadaoRepository cidadaoRepository;

    @Override
    public Optional<Cidadao> getCidadaoByCpf(String cpf) {
        return cidadaoRepository.findById(cpf);
    }

    @Override
    public void removerCidadao(Cidadao cidadao) {
        cidadaoRepository.delete(cidadao);
        
    }

    @Override
    public void salvarCidadao(Cidadao cidadao) {
        cidadaoRepository.save(cidadao);
        
    }

    @Override
    public void cadastrarCidadao(CidadaoDTO cidadaoDTO) {
        Cidadao cidadao = new Cidadao(cidadaoDTO.getCpf(), cidadaoDTO.getNome(), cidadaoDTO.getDataNasc(), cidadaoDTO.getCartaoSus(), 
        cidadaoDTO.getTelefone(), cidadaoDTO.getEmail(), cidadaoDTO.getProfissao(), cidadaoDTO.getComorbidades(), cidadaoDTO.getSenha());
        cidadaoRepository.save(cidadao);
    }

}
    

  

    
