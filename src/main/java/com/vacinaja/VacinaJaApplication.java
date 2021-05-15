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
	
	
	//-----------------------------define quais rotas  precisam de autenticacao (token)-------------------------------------------- 
	@Bean
	public FilterRegistrationBean<TokenFiltro> filterJwt() {
		FilterRegistrationBean<TokenFiltro> filterRB = new FilterRegistrationBean<TokenFiltro>();
		filterRB.setFilter(new TokenFiltro());
		filterRB.addUrlPatterns("/api/atualizar-cidadao","/api/{cpf}/adicionarComorbidades",
				"/api/{cpf}/agendar-vacinacao", "/api/{cpf}/ver-situacao" // cidadao
				
				,"/api/listar-func-n-aprovados", "/api/aprovar-funcionario", "/api/remover-funcionario",// admin
				
				"/api/ativar-comorbidade", "/api/listagem-cidadaos/{comorbidade}","/api/listagem-cidadaos/{profissao}",
				"/api/listagem-cidadaos/{idade}", "/api/habilitar-idade/{idade}", "/api/habilitar-comorbidade/{comorbidade}",
				"/api/habilitar-profissao/{profissao}", "/api/listar-vacinas-disponiveis",
				"/api/checar-situação-cidadaos-para-dose2", // funcionario
				
				"/api/vacinacao/registrar-aplicacao-dose-1", "/api/vacinacao/registrar-aplicacao-dose-2",// aplicacao vacina
				
				"/api/cadastro-lote", "/api/lotes", //lote
				
				"/api/cadastro-vacina", "/api/{id}/alterarNome", "/api/{id}/alterarFabricante", 
				"/api/{id}/alterarDiasParaSegundaDose", "/api/vacinas" // vacina
				);
		
		return filterRB;
	}

}
