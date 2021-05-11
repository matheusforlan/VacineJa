package com.vacinaja.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MetodosAuxiliares {
    public static int calculaIdade(Date dataNasc){
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.setTime(dataNasc);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        dataNascimento.setTime(dataNasc);

        if (hoje.before(dataNascimento)) {

            idade--;
            
        }   
        return idade;  
    }

}
