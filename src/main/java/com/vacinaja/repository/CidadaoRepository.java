package com.vacinaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.vacinaja.model.Cidadao;
import com.vacinaja.model.situacoes.EnumSituacoes;

public interface CidadaoRepository extends JpaRepository<Cidadao, String>{
    
	public List<Cidadao> findBySituacao(EnumSituacoes situacao);
}
