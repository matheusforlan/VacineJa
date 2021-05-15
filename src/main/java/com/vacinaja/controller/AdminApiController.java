package com.vacinaja.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;

import com.vacinaja.DTO.CidadaoDTO;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Funcionario;
import com.vacinaja.service.AdminService;
import com.vacinaja.service.CidadaoService;
import com.vacinaja.service.FuncionarioService;
import com.vacinaja.util.ErroAdmin;
import com.vacinaja.util.ErroCidadao;
import com.vacinaja.util.ErroFuncionario;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AdminApiController {
	
	@Autowired
	AdminService adminService;
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	CidadaoService cidadaoService;
	
	//-------------------------Listar o funcionarios ainda nao aprovado, para o Administrador------------------------
	@RequestMapping(value = "/listar-func-n-aprovados", method = RequestMethod.GET)
	public ResponseEntity<?> listarFuncionariosNaoAprovados(@RequestHeader ("Authorization") String header) {
		
		ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
		
		List<Funcionario> funcionarios = funcionarioService.listarFuncionariosNaoAprovados();
	
			if(funcionarios.isEmpty()){
				return ErroAdmin.ErroNenhumFuncionarioAAprovar();
			}
				
		return new ResponseEntity<List<Funcionario>>(funcionarios,HttpStatus.OK);
			
		
		
	}
	
	//--------------------------- Aprovar um funcionario-----------------------------------------------------------
	@RequestMapping(value = "/aprovar-funcionario", method = RequestMethod.PUT)
	public ResponseEntity<?> aprovarFuncionario(String cpf, @RequestHeader ("Authorization") String header) {
		
		ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
		 
		Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
	        
	    if(!optionalCidadao.isPresent()){
	       return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
	    }
		
		
		Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioByCpf(cpf);
		Funcionario funcionario = optionalFuncionario.get();
		
		if (!optionalFuncionario.isPresent() && (funcionario.getLocalDeTrabalho() == null)) {
			ErroFuncionario.erroFuncionarioNaoEncontrado(cpf);
		}
		
		
		funcionario.setAprovado(true);
		funcionarioService.salvarFuncionario(funcionario);
		
		return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
	}
	
	
	//--------------------------- Remover um funcionario ------------------------------------------------------------
	
	@RequestMapping(value = "/remover-funcionario",method = RequestMethod.DELETE)
	public ResponseEntity<?> removerFuncionario(@RequestBody String cpf, 
			@RequestHeader ("Authorization") String header ) {
		
		ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
		
		Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioByCpf(cpf);
		if (!optionalFuncionario.isPresent()) {
			return ErroFuncionario.erroFuncionarioNaoEncontrado(cpf);
		}
		
		Funcionario funcionario = optionalFuncionario.get();
		
		CidadaoDTO cidadaoDTO = new CidadaoDTO(cpf, funcionario.getNome(), funcionario.getDataNasc(), 
				funcionario.getCartaoSus(), funcionario.getTelefone(), funcionario.getEmail(), 
				funcionario.getProfissao(), funcionario.getComorbidades(), funcionario.getSenha());
		
		funcionarioService.removerFuncionario(cpf);
		cidadaoService.cadastrarCidadao(cidadaoDTO);
		
		return new ResponseEntity<CidadaoDTO>(cidadaoDTO, HttpStatus.OK);
	}
	
	public ResponseEntity<?> validarRequisicao(String header) {
    	ResponseEntity<?> result = null;
    	try {
			if(!adminService.validarRequisicao(header)) {
				result = ErroCidadao.SemPermissao();
				return result;
			}
		} catch (ServletException e) {
			result = ErroCidadao.ErroToken(e.getMessage());
			return result;
		}
    	
    	return result;
    	
    }
}
