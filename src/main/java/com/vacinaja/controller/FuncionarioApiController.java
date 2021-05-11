package com.vacinaja.controller;

import java.util.List;
import java.util.Optional;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.vacinaja.DTO.FuncionarioDTO;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Funcionario;
import com.vacinaja.model.situacoes.EnumSituacoes;
import com.vacinaja.service.CidadaoService;
import com.vacinaja.service.CidadaoServiceImpl;
import com.vacinaja.service.FuncionarioService;
import com.vacinaja.util.ErroCidadao;
import com.vacinaja.util.ErroFuncionario;
import com.vacinaja.util.MetodosAuxiliares;

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
    @RequestMapping(value = "/ativar-comorbidade", method = RequestMethod.POST)
    public ResponseEntity<?> ativarComorbidade(@RequestBody String comorbidade){
        return null;
    }


    // ------------------------Listar Pessoas não habilitdas que possuem alguma comorbidade X ----------------------------------
    @RequestMapping(value = "/listagem-cidadaos/{comorbidade}", method = RequestMethod.GET)
    public ResponseEntity<?> listarCidadaosComComorbidadesAtivadas(@RequestParam String comorbidade){
        List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComComorbidade(comorbidade);
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com essa comorbidade na base de dados.", HttpStatus.NOT_FOUND);
    
       }
       return new ResponseEntity<List<Cidadao>>(cidadaos, HttpStatus.OK);
    }
    
    // ------------------------Listar Pessoas não habilitdas que possuem alguma Profissoa X ----------------------------------
    @RequestMapping(value = "/listagem-cidadaos/{profissao}", method = RequestMethod.GET)
    public ResponseEntity<?> listarCidadaosComProfissaoAtivada(@RequestParam String profissao){
        List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComProfissao(profissao);
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com essa profissão na base de dados.", HttpStatus.NOT_FOUND);
    
        }
        return new ResponseEntity<List<Cidadao>>(cidadaos, HttpStatus.OK);
    }


    // ------------------------Listar Pessoas não habilitdas que possuem a idade maior ou igual a uma idade X ----------------------------
    @RequestMapping(value = "/listagem-cidadaos/{idade}", method = RequestMethod.GET)
    public ResponseEntity<?> listarCidadaosComIdadeMinima(@RequestParam int idade){
        List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComIdadeMinima(idade);
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com idade no mínimo igual a " + idade + ".", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Cidadao>>(cidadaos, HttpStatus.OK);
    }
    // ---------------------------Ativar idade minima -----------------------------------------------------------------------
    @RequestMapping(value = "/habilitar-idade/{idade}", method = RequestMethod.GET)
    public ResponseEntity<?> habilitarCidadaosComIdadeMinima(@RequestParam int idade){
        List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComIdadeMinima(idade);
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com idade no mínimo igual a " + idade + ".", HttpStatus.NOT_FOUND);
        }
        for(Cidadao cidadao : cidadaos){
            cidadao.getSituacao().getSituacao().mudaSituacao(cidadao);
            cidadaoService.salvarCidadao(cidadao);
        }
        return new ResponseEntity<List<Cidadao>>(cidadaos, HttpStatus.OK);
    }





















    //--------------------------------------Métodos auxiliares especificos -------------------------------------------------------

    private List<Cidadao> geraCidadaosNaoHabilitadosComComorbidade(String comorbidade) {
        List<Cidadao> cidadaos = cidadaoService.getCidadaosBySituacao(EnumSituacoes.NAO_HABILITADO);
        for(Cidadao cidadao : cidadaos){
            if(!cidadao.getComorbidades().contains(comorbidade)){
                cidadaos.remove(cidadao);
            }
        }
        return cidadaos;
    }
    private List<Cidadao> geraCidadaosNaoHabilitadosComProfissao(String profissao){
        List<Cidadao> cidadaos = cidadaoService.getCidadaosBySituacao(EnumSituacoes.NAO_HABILITADO);
        for(Cidadao cidadao : cidadaos){
            if(!cidadao.getProfissao().equals(profissao)){
                cidadaos.remove(cidadao);
            }
        }
        return cidadaos;
    }

    private List<Cidadao> geraCidadaosNaoHabilitadosComIdadeMinima(int idade) {
        List<Cidadao> cidadaos = cidadaoService.getCidadaosBySituacao(EnumSituacoes.NAO_HABILITADO);
        for(Cidadao cidadao : cidadaos){
            if(MetodosAuxiliares.calculaIdade(cidadao.getDataNasc()) < idade){
                cidadaos.remove(cidadao);
            }
        }
        return cidadaos;
    }


}


