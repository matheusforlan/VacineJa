package com.vacinaja.controller;

import com.vacinaja.DTO.AplicacaoVacinaDTO;
import com.vacinaja.model.AplicacaoVacina;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Vacina;
import com.vacinaja.model.situacoes.*;
import com.vacinaja.service.*;
import com.vacinaja.util.*;
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

    @Autowired
    LoteService loteService;
    
    @Autowired
    FuncionarioService funcionarioService;

    //---------------------------------Cadastro de aplicacao da primeira dose da vacina ------------------------------------
    @RequestMapping(value = "/vacinacao/registrar-aplicacao-dose-1", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarAplicacaoDose1(@RequestBody AplicacaoVacinaDTO aplicacaoVacinaDTO,
    		@RequestHeader ("Authorization") String header){
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  validarRequisicao(header);
    	
    	//<AplicacaoVacina> optionalAplicacaoVacina = aplicacaoVacinaService.getById(aplicacaoVacinaDTO.getCpfCidadao());
        Optional<Vacina> optionalVacina = vacinaService.getVacinaById(aplicacaoVacinaDTO.getIdVacina());
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(aplicacaoVacinaDTO.getCpfCidadao());
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(aplicacaoVacinaDTO.getCpfCidadao());
        }
        Cidadao cidadao = optionalCidadao.get();
        

        if(!optionalVacina.isPresent()){
            return ErroVacina.erroVacinaNaoEncontrada(aplicacaoVacinaDTO.getIdVacina());
        }
        Vacina vacina = optionalVacina.get();

        Situacao situacao = cidadao.getSituacao().getSituacao();

        
        if(situacao instanceof NaoHabilitado){
            return ErroAplicacao.erroCidadaoNaoHabilitadoParaVacina(aplicacaoVacinaDTO.getCpfCidadao());
        }
        if(situacao instanceof TomouDose1 || situacao instanceof HabilitadoDose2 || situacao instanceof VacinacaoFinalizada){
            return ErroAplicacao.erroCidadaoJaTomouDose1(aplicacaoVacinaDTO.getCpfCidadao());
        }

        if (!loteService.isVacinaDisponivel(aplicacaoVacinaDTO.getIdVacina())) {
            return ErroAplicacao.erroVacinaIndisponivel(aplicacaoVacinaDTO.getIdVacina());
        }
        

        situacao.tomaVacina(cidadao, vacina);

        loteService.tomarDose(vacina.getId());

        if (!loteService.isVacinaDisponivel(vacina.getId())) {
            vacina.tornaIndisponivel();
        }

        cidadaoService.salvarCidadao(cidadao);


        AplicacaoVacina aplicacaoVacina = new AplicacaoVacina(cidadao, vacina, aplicacaoVacinaDTO.getDataAplicacao());

        aplicacaoVacinaService.salvar(aplicacaoVacina);

        return new ResponseEntity<AplicacaoVacina>(aplicacaoVacina, HttpStatus.CREATED);
    }

     //---------------------------------Cadastro de aplicacao da segunda dose da vacina ------------------------------------
    @RequestMapping(value = "/vacinacao/registrar-aplicacao-dose-2", method = RequestMethod.PUT)
    public ResponseEntity<?> cadastrarAplicacaoDose2(@RequestBody AplicacaoVacinaDTO aplicacaoVacinaDTO,
    		@RequestHeader ("Authorization") String header){
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  validarRequisicao(header);
    	
    	Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(aplicacaoVacinaDTO.getCpfCidadao());
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(aplicacaoVacinaDTO.getCpfCidadao());
        }
        Cidadao cidadao = optionalCidadao.get();

        Situacao situacao = cidadao.getSituacao().getSituacao();

        if(situacao instanceof NaoHabilitado){
            return ErroAplicacao.erroCidadaoNaoHabilitadoParaVacina(aplicacaoVacinaDTO.getCpfCidadao());
        }
        if(situacao instanceof VacinacaoFinalizada){
            return ErroAplicacao.erroCidadaoJaFinalizouVacinacao(aplicacaoVacinaDTO.getCpfCidadao());
        }

        Optional<AplicacaoVacina> optionalAplicacaoVacina = aplicacaoVacinaService.getById(aplicacaoVacinaDTO.getCpfCidadao());
        if(!optionalAplicacaoVacina.isPresent()){
            return ErroAplicacao.erroCidadaoNaoTomouDose1(aplicacaoVacinaDTO.getCpfCidadao());
        }
        
        AplicacaoVacina aplicacao = optionalAplicacaoVacina.get();
        
        if(aplicacao.getVacina().getId() != aplicacaoVacinaDTO.getIdVacina()){
            return ErroAplicacao.erroVacinaInconsistente(aplicacaoVacinaDTO.getIdVacina());
        }

        if (!loteService.isVacinaDisponivel(aplicacaoVacinaDTO.getIdVacina())) {
            return ErroAplicacao.erroVacinaIndisponivel(aplicacaoVacinaDTO.getIdVacina());
        }


        situacao.tomaVacina(cidadao, aplicacao.getVacina());

        loteService.tomarDose(aplicacao.getVacina().getId());

        if (!loteService.isVacinaDisponivel(aplicacao.getVacina().getId())) {
            aplicacao.getVacina().tornaIndisponivel();
        }

        cidadaoService.salvarCidadao(cidadao);

        aplicacao.tomouDose2();

        aplicacaoVacinaService.salvar(aplicacao);

        return new ResponseEntity<AplicacaoVacina>(aplicacao, HttpStatus.ACCEPTED);   
    }
    
    public ResponseEntity<?> validarRequisicao(String header) {
    	ResponseEntity<?> result = null;
    	try {
			if(!funcionarioService.validarRequisicao(header)) {
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
