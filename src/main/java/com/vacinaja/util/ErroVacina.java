package com.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroVacina {

    static final String VACINA_NAO_CADASTRADA = "Esta vacina não está cadastrada.";
	
	static final String NAO_HA_VACINAS_CADASTRADAS = "Não há vacinas cadastradas.";

	static final String NAO_FOI_POSSIVEL_ATUALIZAR_VACINA = "Não foi possível atualizar a situação desta vacina.";

	static final String VACINA_JA_CADASTRADA = "Esta vacina já esta cadastrado";
    


    public static ResponseEntity<CustomErrorType> erroVacinaNaoEncontrada(Long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroVacina.VACINA_NAO_CADASTRADA, id)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroSemVacinasCadastradas() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroVacina.NAO_HA_VACINAS_CADASTRADAS),
				HttpStatus.NO_CONTENT);
	}

	public static ResponseEntity<?> erroAtualizarVacina(Long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroVacina.NAO_FOI_POSSIVEL_ATUALIZAR_VACINA, id)),
         HttpStatus.NOT_MODIFIED);
	}
	
	public static ResponseEntity<?> erroVacinaJaCadastrada(Long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroVacina.VACINA_JA_CADASTRADA,
				id)), HttpStatus.CONFLICT);
	}
    
}
