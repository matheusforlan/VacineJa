package com.vacinaja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroAgendamentoVacinacao {

    static final String VACINACAO_JA_AGENDADA_NESTE_HORARIO_E_DATA =  "Já existe uma vacinação agendada para este horário e data.";

    public static ResponseEntity<CustomErrorType> erroDataHorarioJaAgendados(long id) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAgendamentoVacinacao.VACINACAO_JA_AGENDADA_NESTE_HORARIO_E_DATA, id)), HttpStatus.CONFLICT);
    }
    
}
