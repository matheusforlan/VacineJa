package com.vacinaja.controller;

import java.util.Optional;

import com.vacinaja.DTO.AplicacaoVacinaDTO;
import com.vacinaja.model.AplicacaoVacina;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Vacina;
import com.vacinaja.repository.AplicacaoVacinaRespository;
import com.vacinaja.service.*;
import com.vacinaja.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AplicacaoVacinaApiController {
    @Autowired 
    AplicacaoVacinaService aplicacaoVacinaService;

    @Autowired
    VacinaService vacinaService;

    @Autowired
    CidadaoService cidadaoService;

    //---------------------------------Cadastro de aplicacao da primeira dose da vacina ------------------------------------
    @RequestMapping(value = "/vacinacao/registrar-aplicacao-dose-1", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarAplicacaoDose1(@RequestBody AplicacaoVacinaDTO aplicacaoVacinaDTO){
        Optional<AplicacaoVacina> optionalAplicacaoVacina = aplicacaoVacinaService.getById(aplicacaoVacinaDTO.getCpfCidadao());
        Optional<Vacina> optionalVacina = vacinaService.getVacinaById(aplicacaoVacinaDTO.getIdVacina());
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(aplicacaoVacinaDTO.getCpfCidadao());
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(aplicacaoVacinaDTO.getCpfCidadao());
        }
        Cidadao cidadao = optionalCidadao.get();
        

        if(!optionalVacina.isPresent()){
            return ErroVacina.erroVacinaNaoEncontrada(aplicacaoVacinaDTO.getIdVacina());
        }

        //verificacao temporaria.
        //Futuramente a verificacao acontecera verficando o estado do cidadao para a vacinacao 
        if(optionalAplicacaoVacina.isPresent()){
            return ErroAplicacao.erroCidadaoJaTomouDose1(aplicacaoVacinaDTO.getCpfCidadao());
        }

        /*
        if(cidadao.naoHabilitadoParaVacina() ){
            return ErroAplicacao.cidadaoNaoHabilitadoParaVacina(aplicacaoVacinaDTO.getCpfCidadao());
        }
        if(cidadao.esperandoDose2() || cidadao.habilitadoDose2() || cidadao.imunizado()){
            return ErroAplicacao.cidadaoJaTomouDose1(aplicacaoVacinaDTO.getCpfCidadao());
        }
        if(!loteService.possui(aplicacaoVacinaDTO.getVacinaId())){
            return ErroLote.vacinaIndisponivelNoMomento(aplicacaoVacinaDTO.getVacinaId());
        }    
        */
        

        Vacina vacina = optionalVacina.get();

        //vacina.Aplicar(cidadao); esse metodo em vacina ira alterar o estado do cidadao de acordo com as regras especificas da vacina
        //loteService.usarDose(vacina.getId());

        //cidadaoService.salvar(cidadao);
        

        AplicacaoVacina aplicacaoVacina = new AplicacaoVacina(cidadao, vacina, aplicacaoVacinaDTO.getDataAplicacao());

        aplicacaoVacinaService.salvar(aplicacaoVacina);

        return new ResponseEntity<AplicacaoVacina>(aplicacaoVacina, HttpStatus.CREATED);
    }

     //---------------------------------Cadastro de aplicacao da segunda dose da vacina ------------------------------------
    @RequestMapping(value = "/vacinacao/registrar-aplicacao-dose-2", method = RequestMethod.PUT)
    public ResponseEntity<?> cadastrarAplicacaoDose2(@RequestBody AplicacaoVacinaDTO aplicacaoVacinaDTO){
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(aplicacaoVacinaDTO.getCpfCidadao());
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(aplicacaoVacinaDTO.getCpfCidadao());
        }
        Cidadao cidadao = optionalCidadao.get();
        Optional<AplicacaoVacina> optionalAplicacaVacina = aplicacaoVacinaService.getById(aplicacaoVacinaDTO.getCpfCidadao());
        if(!optionalAplicacaVacina.isPresent()){
            return ErroAplicacao.erroCidadaoNaoTomouDose1(aplicacaoVacinaDTO.getCpfCidadao());
        }
        AplicacaoVacina aplicacao = optionalAplicacaVacina.get();
        if(aplicacao.getVacina().getId() != aplicacaoVacinaDTO.getIdVacina()){
            return ErroAplicacao.erroVacinaInconsistente(aplicacaoVacinaDTO.getIdVacina());
        }
        if(aplicacao.getVacina().getQuantidadeDoses() == 1){
            return ErroVacina.erroVacinaNaoTemDuasDoses(aplicacaoVacinaDTO.getIdVacina());
        }

        aplicacao.tomouDose2();
        aplicacaoVacinaService.salvar(aplicacao);

        return new ResponseEntity<AplicacaoVacina>(aplicacao, HttpStatus.ACCEPTED);
        
    }
    
}
