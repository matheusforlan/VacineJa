package com.vacinaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacinaja.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String>{

}
