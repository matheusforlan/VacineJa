package com.vacinaja.util;

import com.vacinaja.util.CustomErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroLote {

    static final String SEM_LOTES_CADASTRADOS = "Não há lotes cadastrados no sistema.";

    static final String ID_INVALIDO = "Este id já está cadastrado em outro lote do sistema.";

    public static ResponseEntity<CustomErrorType> erroSemLotesCadastrados() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLote.SEM_LOTES_CADASTRADOS), HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<CustomErrorType> erroIdInvalido(Long id) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroLote.ID_INVALIDO, id)), HttpStatus.CONFLICT);
    }
    
}
