package com.vacinaja.controller;

import java.sql.Date;
import java.util.Optional;

import com.vacinaja.DTO.CidadaoDTO;
import com.vacinaja.model.Cidadao;
import com.vacinaja.service.CidadaoService;
import com.vacinaja.util.ErroCidadao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CidadaoApiController {
    
    @Autowired
    CidadaoService cidadaoService;
 
    // ------------------------------------------ cadastro de cidadao ------------------------------------------
    @RequestMapping(value = "/cadastro-cidadao", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarCidadao(@RequestBody CidadaoDTO cidadaoDTO, UriComponentsBuilder ucBuilder){
        Optional<Cidadao> cidadao = cidadaoService.getCidadaoByCpf(cidadaoDTO.getCpf());

        if(!cidadao.isPresent()){
            return ErroCidadao.erroCidadaoJaCadastrado(cidadaoDTO.getCpf());
        }
        cidadaoService.cadastrarCidadao(cidadaoDTO);


        return new ResponseEntity<CidadaoDTO>(cidadaoDTO, HttpStatus.OK);
    }


    
    // ------------------------------------------ metodos de atualizacao de dados usuario ------------------------------------------

    // alterar nome do cidadao
    @RequestMapping(value = "/{cpf}/alterarNome", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarNome(@PathVariable("cpf") String cpf, @RequestBody String nome){
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        Cidadao cidadao = optionalCidadao.get();
        cidadao.setNome(nome);

        cidadaoService.salvarCidadao(cidadao);

        return new ResponseEntity<Cidadao>(cidadao, HttpStatus.OK);
    }

    //alterar telefone do cidadao
    @RequestMapping(value = "/{cpf}/alterarTelefone", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarTelefone(@PathVariable("cpf") String cpf, @RequestBody String telefone){
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        Cidadao cidadao = optionalCidadao.get();
        cidadao.setTelefone(telefone);

        cidadaoService.salvarCidadao(cidadao);

        return new ResponseEntity<Cidadao>(cidadao, HttpStatus.OK);
    }


    //alterar email do cidadao
    @RequestMapping(value = "/{cpf}/alterarEmail", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarEmail(@PathVariable("cpf") String cpf, @RequestBody String email){
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        Cidadao cidadao = optionalCidadao.get();
        cidadao.setEmail(email);

        cidadaoService.salvarCidadao(cidadao);

        return new ResponseEntity<Cidadao>(cidadao, HttpStatus.OK);
    }

    //alterar profissao do cidadao
    @RequestMapping(value = "/{cpf}/alterarProfissao", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarProfissao(@PathVariable("cpf") String cpf, @RequestBody String profissao){
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        Cidadao cidadao = optionalCidadao.get();
        cidadao.setProfissao(profissao);

        cidadaoService.salvarCidadao(cidadao);

        return new ResponseEntity<Cidadao>(cidadao, HttpStatus.OK);
    }

    
    //alterar comorbidades do cidadao
    @RequestMapping(value = "/{cpf}/alterarComorbidades", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarComorbidades(@PathVariable("cpf") String cpf, @RequestBody String comorbidades){
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        Cidadao cidadao = optionalCidadao.get();
        cidadao.setComorbidades(comorbidades);

        cidadaoService.salvarCidadao(cidadao);

        return new ResponseEntity<Cidadao>(cidadao, HttpStatus.OK);
    }

    //adicionar comorbidades do cidadao
    @RequestMapping(value = "/{cpf}/adicionarComorbidades", method = RequestMethod.PUT)
    public ResponseEntity<?> adicionarComorbidade(@PathVariable("cpf") String cpf, @RequestBody String comorbidades){
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        Cidadao cidadao = optionalCidadao.get();
        cidadao.adicionarComorbidades(comorbidades);

        cidadaoService.salvarCidadao(cidadao);

        return new ResponseEntity<Cidadao>(cidadao, HttpStatus.OK);
    }


    //alterar data de nascimento do cidadao
    @RequestMapping(value = "/{cpf}/alterarDataNascimento", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarDataNascimento(@PathVariable("cpf") String cpf, @RequestBody Date dataNasc){
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        Cidadao cidadao = optionalCidadao.get();
        cidadao.setDataNasc(dataNasc);

        cidadaoService.salvarCidadao(cidadao);

        return new ResponseEntity<Cidadao>(cidadao, HttpStatus.OK);
    }
}
