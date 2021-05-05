package com.vacinaja.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.vacinaja.DTO.AdminDTO;
import com.vacinaja.model.Admin;
import com.vacinaja.model.Funcionario;
import com.vacinaja.service.AdminService;
import com.vacinaja.service.FuncionarioService;
import com.vacinaja.util.ErroAdmin;
import com.vacinaja.util.ErroFuncionario;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AdminApiController {
	
	@Autowired
	AdminService adminService;
	@Autowired
	FuncionarioService funcionarioService;
	
	//-----------------------cadastro do Admistrador do sistema --------------------------------------------------
	@RequestMapping(value = "/cadastro-admin", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarAdmin(@RequestBody AdminDTO adminDTO, UriComponentsBuilder ucBuilder) {
		
		Optional<Admin> optionalAdmin = adminService.getAdminByCpf(adminDTO.getCpf());
		
		if (optionalAdmin.isPresent()) {
			return ErroAdmin.ErroAdminJaCadastrado(adminDTO);
		}
		
		adminService.cadastrarAdmin(adminDTO);
		
		
		return new ResponseEntity<AdminDTO>(adminDTO, HttpStatus.OK);
		
	
	}
	
	
	//-------------------------Listar o funcionarios ainda nao aprovado, para o Administrador------------------------
	@RequestMapping(value = "/listar-func-n-aprovados", method = RequestMethod.GET)
	public ResponseEntity<?> listarFuncionariosNaoAprovados() {
		
		List<Funcionario> funcionarios = funcionarioService.listarFuncionariosNaoAprovados();
		
		if (funcionarios.isEmpty()) {
			return ErroAdmin.ErroNenhumFuncionarioAAprovar();
		}
		
		return new ResponseEntity<List<Funcionario>>(funcionarios,HttpStatus.OK);
		
	
	}
	
	//--------------------------- Aprovar um funcionario-----------------------------------------------------------
	@RequestMapping(value = "/aprovar-funcionario", method = RequestMethod.POST)
	public ResponseEntity<?> aprovarFuncionario(String cpf) {
		
		Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioByCpf(cpf);
		
		if (!optionalFuncionario.isPresent()) {
			ErroFuncionario.erroFuncionarioNaoEncontrado(cpf);
		}
		Funcionario funcionario = optionalFuncionario.get();
		
		
		funcionarioService.salvarFuncionario(funcionario);
		funcionario.setAprovado(true);
		
		return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
		
	}
	
	
	

}
