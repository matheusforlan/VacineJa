package com.vacinaja.service;


import java.sql.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinaja.filtros.TokenFiltro;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.RespostaLogin;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public final class JwtService {
	
	@Autowired
	CidadaoService cidadaoService;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired 
	AdminService adminService;
	
	private final String TOKEN_KEY = "login do robin";

	public RespostaLogin autenticar(String cpf, String senha) {
		
		if (!cidadaoService.validarUsuarioSenha(cpf, senha)) {
			 return new RespostaLogin("Usuario ou senha inválidos. Não foi possível realizar o login.")	;
			
		} 
		
		String token = gerarToken(cpf);
		
		return new RespostaLogin(token);
		
	}
	
	
	

	private String gerarToken(String id) {
		return Jwts.builder().setSubject(id)
				.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)).compact(); // 1 hora
	}
	
	
	
	public String getIdbyToken(String header) throws ServletException {
		 if ( header == null || !header.startsWith("Bearer ")) {
			 throw new ServletException("Token inexistente ou mal formado.");
		 }
		 String token = header.substring(TokenFiltro.TOKEN_INDEX);
		 
		 String idRequest = null;
		 try {
			  idRequest = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token)
					 .getBody().getSubject();
		 }catch (SignatureException| ExpiredJwtException e) {
			 throw new ServletException("Token invalido ou expirado.");
		 }
		 return idRequest;
	}

	
	
	
}
