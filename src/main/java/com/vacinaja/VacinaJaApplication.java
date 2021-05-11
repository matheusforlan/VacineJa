package com.vacinaja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.vacinaja.filtros.*;

@SpringBootApplication
public class VacinaJaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacinaJaApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<TokenFiltro> filterJwt() {
		FilterRegistrationBean<TokenFiltro> filterRB = new FilterRegistrationBean<TokenFiltro>();
		filterRB.setFilter(new TokenFiltro());
		filterRB.addUrlPatterns("/api/cadastro-cidadao", "/api/atualizar-cidadao","/{cpf}/adicionarComorbidades",
				"/{cpf}/agendar-vacinacao");
		return filterRB;
	}

}
