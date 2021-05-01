package com.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroFuncionario {
	
	static final String FUNCIONARIO_NAO_CASTRADO = "Funcionário com cpf %s não está cadastrado.";	

	static final String FUNCIONARIO_JA_CADASTRADO = "O funcionário com cpf %s já está cadastrado.";
    


    public static ResponseEntity<CustomErrorType> erroFuncionarioNaoEncontrado(String cpf) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroFuncionario.FUNCIONARIO_NAO_CASTRADO, cpf)),
				HttpStatus.NOT_FOUND);
	}
	
		
	public static ResponseEntity<?> erroFuncionarioJaCadastrado(String cpf) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroFuncionario.FUNCIONARIO_JA_CADASTRADO,
				cpf)), HttpStatus.CONFLICT);
	}
}
