package com.vacinaja.controller;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vacinaja.model.Cidadao;
import com.vacinaja.model.RespostaLogin;
import com.vacinaja.service.JwtService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	JwtService jwtService;
	
	@RequestMapping (path = "/login", method = RequestMethod.POST)
	public ResponseEntity<RespostaLogin> autenticarCidadao(@RequestBody String cpf, String senha) throws ServletException {
		return new ResponseEntity<RespostaLogin> (jwtService.autenticar(cpf,senha), HttpStatus.OK);
		
	}
	
	
	

}
