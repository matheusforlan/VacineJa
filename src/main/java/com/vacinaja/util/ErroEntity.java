package com.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vacinaja.DTO.EntityDTO;
import com.vacinaja.model.Entitys;

public class ErroEntity {
	
	static final String ENTITY_NAO_CASTRADO = "Entity com id %s não está cadastrado";
	
	static final String ENTITYS_NAO_CASTRADOS = "Não há Entitys cadastrados";

	static final String NAO_FOI_POSSIVEL_ATUALIZAR = "Não foi possível mudar atualizar a situação do Entity %s "
			+ "do frabricante %s";

	static final String ENTITY_JA_CADASTRADO = "O Entity %s do fabricante %s já esta cadastrado";

	public static ResponseEntity<CustomErrorType> erroEntityNaoEnconrtrado(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroEntity.ENTITY_NAO_CASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroSemEntitysCadastrados() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroEntity.ENTITYS_NAO_CASTRADOS),
				HttpStatus.NO_CONTENT);
	}

	public static ResponseEntity<?> erroMudarSituacao(Entitys entitys) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroEntity.NAO_FOI_POSSIVEL_ATUALIZAR,
				entitys.getNome())), HttpStatus.NOT_MODIFIED);
	}
	
	public static ResponseEntity<?> erroEntityJaCadastrado(EntityDTO entityDTO) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroEntity.ENTITY_JA_CADASTRADO,
				entityDTO.getNome())), HttpStatus.CONFLICT);
	}
}
