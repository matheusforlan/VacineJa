package com.vacinaja.repository;

import com.vacinaja.model.Vacina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacina, Long>{
    
}
