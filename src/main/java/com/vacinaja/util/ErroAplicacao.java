package com.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroAplicacao {
    static final String CIDADAO_JA_TOMOU_DOSE1 = "O cidadao com cpf %s ja tomou a dose 1 da vacina.";
	
	static final String CIDADAO_NAO_HABILITADO = "O cidadao com cpf %s não está habilitado para vacinação";

    static final String VACINA_INCONSISTENTE = "O id da vacina: %s não é correspondente com o id da vacina da dose 1.";

    static final String CIDADAO_NAO_TOMOU_DOSE1 = "O cidadão ainda não tomou a primeira dose dessa vacina";

	static final String CIDADAO_JA_FINALIZOU_VACINACAO = "O cidadão com cpf %s já finalizou sua vacinação..";

	static final String VACINA_INDISPONIVEL = "Esta vacina não possui doses disponíveis no momento.";


    public static ResponseEntity<CustomErrorType> erroCidadaoJaTomouDose1(String cpf) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAplicacao.CIDADAO_JA_TOMOU_DOSE1, cpf)),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<CustomErrorType> erroCidadaoNaoHabilitadoParaVacina(String cpf) {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAplicacao.CIDADAO_NAO_HABILITADO, cpf)),
				HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<?> erroVacinaInconsistente(long idVacina) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAplicacao.VACINA_INCONSISTENTE, idVacina)),
         HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<?> erroCidadaoNaoTomouDose1(String cpf) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAplicacao.CIDADAO_NAO_TOMOU_DOSE1,	cpf)), 
        HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> erroCidadaoJaFinalizouVacinacao(String cpf){
		return new ResponseEntity<CustomErrorType> (new CustomErrorType(String.format(ErroAplicacao.CIDADAO_JA_FINALIZOU_VACINACAO, cpf)), 
		HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> erroVacinaIndisponivel(Long vacinaId){
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAplicacao.VACINA_INDISPONIVEL, vacinaId)), HttpStatus.NO_CONTENT);
	}
}
