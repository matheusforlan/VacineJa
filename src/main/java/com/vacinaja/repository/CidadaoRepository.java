package com.vacinaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacinaja.model.Cidadao;

public interface CidadaoRepository extends JpaRepository<Cidadao, String>{
    
}
