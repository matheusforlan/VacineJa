package com.vacinaja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vacinaja.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, String>{

	
	List<Funcionario> findByIsAprovadoFalse();

	List<Funcionario> findAllByIsAprovadoFalse();

}
