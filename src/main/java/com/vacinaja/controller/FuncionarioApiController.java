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
	
//	@Autowired
    FuncionarioService funcionarioService;
    CidadaoService cidadaoService;
	
	// ------------------------------------------ cadastro de funcionário ------------------------------------------
    @RequestMapping(value = "/cadastro-funcionario", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO, UriComponentsBuilder ucBuilder){
               
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(funcionarioDTO.getCpf());
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(funcionarioDTO.getCpf());
        }
        
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioByCpf(funcionarioDTO.getCpf());
        
        if(!funcionario.isPresent()){
            return ErroFuncionario.erroFuncionarioJaCadastrado(funcionarioDTO.getCpf());
        }
        
        // Precisa da autorização do administrador do sistema.
        
        funcionarioService.cadastrarFuncionario(funcionarioDTO);

        return new ResponseEntity<FuncionarioDTO>(funcionarioDTO, HttpStatus.OK);
    }

}
