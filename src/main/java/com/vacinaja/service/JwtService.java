package com.vacinaja.service;


import java.sql.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinaja.filtros.TokenFiltro;
import com.vacinaja.model.Cidadao;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public final class JwtService {
	
	@Autowired
	CidadaoService cidadaoService;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	private final String TOKEN_KEY = "login do robin";

	public RespostaLogin autenticarCidadao(Cidadao cidadao) {
		
		if (!cidadaoService.validarUsuarioSenha(cidadao)) {
			new RespostaLogin("Usuario ou senha inválidos. Não foi possível realizar o login.")	;
			
		} 
		
		String token = gerarToken(cidadao.getCpf());
		
		return new RespostaLogin(token);
		
	}

	private String gerarToken(String id) {
		return Jwts.builder().setSubject(id)
				.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)).compact(); // 1 hora
	}
	
	public String getIdbyToken(String authorizationHeader) throws ServletException{
		
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		
		String token = authorizationHeader.substring(TokenFiltro.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new ServletException("Token invalido ou expirado!");
		}
		return subject;
	}
	
	
}
