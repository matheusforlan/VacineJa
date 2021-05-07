package com.vacinaja.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.vacinaja.model.AplicacaoVacina;


public interface AplicacaoVacinaRespository extends JpaRepository<AplicacaoVacina, String>{
    public Optional<AplicacaoVacina> getByCpfCidadao(String cpf);

    public List<AplicacaoVacina> findByVacinaId(long id);
}
