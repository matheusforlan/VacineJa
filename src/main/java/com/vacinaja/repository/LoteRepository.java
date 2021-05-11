package com.vacinaja.repository;

import java.util.List;

import com.vacinaja.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<Lote, Long>{
    public List<Lote> findByVacinaId(long id);
}
