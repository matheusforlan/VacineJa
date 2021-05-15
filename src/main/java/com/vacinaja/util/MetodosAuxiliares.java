package com.vacinaja.util;



import java.util.Date;


import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Years;

public class MetodosAuxiliares {
    public static int calculaIdade(Date dataNasc){
        DateTime hoje = new DateTime();
        DateTime dataNascimento = new DateTime(dataNasc);
        
        Years anos = Years.yearsBetween(dataNascimento, hoje);
        return anos.getYears();

        
        /*
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.setTime(dataNasc);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (hoje.before(dataNascimento)) {

            idade--;
            
        }   
        return idade;  
        */
        

    }

    public static int caculaDias(java.sql.Date dataAplicacao) {
        DateTime hoje = new DateTime();
        DateTime data = new DateTime(dataAplicacao);
        
        Days dias = Days.daysBetween(hoje, data);
        return dias.getDays();

    }

}
