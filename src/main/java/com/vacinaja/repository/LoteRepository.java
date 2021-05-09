package com.vacinaja.repository;

import com.vacinaja.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<Lote, Long>{
    
}
