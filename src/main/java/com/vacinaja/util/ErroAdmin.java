package com.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vacinaja.model.Admin;

public class ErroAdmin {

static final String admin_NAO_CADASTRADO = "Administrador com cpf %s não está cadastrado";
	
	static final String Admin_NAO_CADASTRADOS = "Não há adminiastradores cadastrados";

	static final String NAO_FOI_POSSIVEL_ATUALIZAR = "Não foi possível mudar atualizar a situação do administrador %s "
			+ "do frabricante %s";

	static final String admin_JA_CADASTRADO = "O administrador %s já esta cadastrado";
	
	static final String NENHUM_FUNCIONARIO_A_APROVAR = "Nenhum pedido de aprovação encontrado"; 

	public static ResponseEntity<CustomErrorType> ErroAdminNaoEnconrtrado(String id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAdmin.admin_NAO_CADASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<?> erroMudarSituacao(Admin admin) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAdmin.NAO_FOI_POSSIVEL_ATUALIZAR,
				admin.getUser())), HttpStatus.NOT_MODIFIED);
	}
	
	public static ResponseEntity<?> ErroNenhumFuncionarioAAprovar() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroAdmin.NENHUM_FUNCIONARIO_A_APROVAR)
				, HttpStatus.NO_CONTENT);
	}
}
