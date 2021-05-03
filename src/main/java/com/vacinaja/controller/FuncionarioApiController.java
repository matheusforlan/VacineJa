package com.vacinaja.controller;

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

import com.vacinaja.DTO.CidadaoDTO;
import com.vacinaja.DTO.FuncionarioDTO;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Funcionario;
import com.vacinaja.service.CidadaoService;
import com.vacinaja.service.FuncionarioService;
import com.vacinaja.util.ErroCidadao;
import com.vacinaja.util.ErroFuncionario;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FuncionarioApiController {
	
	@Autowired
    FuncionarioService funcionarioService;
	@Autowired
	CidadaoService cidadaoService;
	
	// ------------------------------------------ cadastro de funcionário ------------------------------------------
    @RequestMapping(value = "/cadastro-funcionario", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO, UriComponentsBuilder ucBuilder){
               
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(funcionarioDTO.getCpf());
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(funcionarioDTO.getCpf());
        }
        
        Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioByCpf(funcionarioDTO.getCpf());
        
        if(optionalFuncionario.isPresent()) {
            return ErroFuncionario.erroFuncionarioJaCadastrado(funcionarioDTO.getCpf());
        }
        
        Cidadao cidadao = optionalCidadao.get();
        
        //removendo o cidadao com mesmo id, pra n dar conflito
        cidadaoService.removerCidadao(cidadao);
        
       
        
        //pra poder retornar o objeto certo, ja que ele n ta aprovado,
        funcionarioDTO.setAprovado(false);
        
        // Precisa da autorização do administrador do sistema.
        funcionarioService.cadastrarFuncionario(funcionarioDTO);   

        return new ResponseEntity<FuncionarioDTO>(funcionarioDTO, HttpStatus.OK);
    }

}
