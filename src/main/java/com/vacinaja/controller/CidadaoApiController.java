package com.vacinaja.controller;

import com.vacinaja.DTO.AgendamentoVacinacaoDTO;
import com.vacinaja.DTO.CidadaoDTO;
import com.vacinaja.model.AgendamentoVacinacao;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.situacoes.EnumSituacoes;
import com.vacinaja.model.situacoes.HabilitadoDose1;
import com.vacinaja.service.AgendamentoVacinacaoService;
import com.vacinaja.service.CidadaoService;
import com.vacinaja.util.ErroAgendamentoVacinacao;
import com.vacinaja.util.ErroAplicacao;
import com.vacinaja.util.ErroCidadao;
import java.util.Optional;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/api")
@CrossOrigin
public class CidadaoApiController {
    
    @Autowired
    CidadaoService cidadaoService;

    @Autowired
    AgendamentoVacinacaoService agendamentoVacinacaoService;
 
    // ------------------------------------------ cadastro de cidadao ------------------------------------------
    @RequestMapping(value = "/cadastro-cidadao", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarCidadao(@RequestBody CidadaoDTO cidadaoDTO, UriComponentsBuilder ucBuilder){
        Optional<Cidadao> cidadao = cidadaoService.getCidadaoByCpf(cidadaoDTO.getCpf());

        if(cidadao.isPresent()){
            return ErroCidadao.erroCidadaoJaCadastrado(cidadaoDTO.getCpf());
        }
        cidadaoService.cadastrarCidadao(cidadaoDTO);


        return new ResponseEntity<CidadaoDTO>(cidadaoDTO, HttpStatus.OK);
    }

    
    // ------------------------------------------ atualização de dados do cidadao ------------------------------------------
    @RequestMapping(value = "/atualizar-cidadao", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarCidadao(@RequestBody CidadaoDTO cidadaoDTO, 
    		@RequestHeader ("Authorization") String header) {
    	
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header, cidadaoDTO.getCpf());
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cidadaoDTO.getCpf());

        if (!optionalCidadao.isPresent()) {
            return ErroCidadao.erroCidadaoNaoEncontrado(cidadaoDTO.getCpf());
        }

        Cidadao cidadao = optionalCidadao.get();
        
       

        if (cidadao.getSenha().equals(cidadaoDTO.getSenha())) {
            cidadaoService.atualizarCidadao(cidadaoDTO, cidadao);
            cidadaoService.salvarCidadao(cidadao);
        }

        return new ResponseEntity<Cidadao>(cidadao, HttpStatus.OK);
    }

    //adicionar comorbidades do cidadao
    @RequestMapping(value = "/{cpf}/adicionarComorbidades", method = RequestMethod.PUT)
    public ResponseEntity<?> adicionarComorbidade(@PathVariable("cpf") String cpf, @RequestBody String comorbidades,
    		@RequestHeader("Authorization") String header) {
       
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header, cpf);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        Cidadao cidadao = optionalCidadao.get();
        cidadao.adicionarComorbidades(comorbidades);

        cidadaoService.salvarCidadao(cidadao);

        return new ResponseEntity<Cidadao>(cidadao, HttpStatus.OK);
    }

    // ------------------------------------------ agendamento de vacinação do cidadao ------------------------------------------
    @RequestMapping(value = "/agendar-vacinacao", method = RequestMethod.POST)
    public ResponseEntity<?> agendarVacinacao(@RequestBody AgendamentoVacinacaoDTO agendamentoVacinacaoDTO,
    		@RequestHeader ("Authorization") String header) {
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header, agendamentoVacinacaoDTO.getCpfCidadao());
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	
    	Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(agendamentoVacinacaoDTO.getCpfCidadao());

        if (!optionalCidadao.isPresent()) {
            return ErroCidadao.erroCidadaoNaoEncontrado(agendamentoVacinacaoDTO.getCpfCidadao());
        }

        
        Cidadao cidadao = optionalCidadao.get();

        
        if (!cidadaoHabilitadoDose1(cidadao)) {
            return ErroAplicacao.erroCidadaoNaoHabilitadoParaVacina(agendamentoVacinacaoDTO.getCpfCidadao());
        }

        agendamentoVacinacaoService.cadastrarAgendamentoVacinacao(agendamentoVacinacaoDTO, cidadao);

        return new ResponseEntity<AgendamentoVacinacaoDTO>(agendamentoVacinacaoDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{cpf}/ver-situacao", method = RequestMethod.GET)
    public ResponseEntity<?> verSituacaoCidadao(@RequestParam String cpf,
    		@RequestHeader ("Authorization") String header) {
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header, cpf);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);

        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        Cidadao cidadao = optionalCidadao.get();
        return new ResponseEntity<String>(cidadao.toString(), HttpStatus.OK);
    }

    
    //--------------------------------------Métodos auxiliares especificos -------------------------------------------------------
    
    private boolean cidadaoHabilitadoDose1(Cidadao cidadao) {
        EnumSituacoes situacao = cidadao.getSituacao();

        return (situacao.getSituacao() instanceof HabilitadoDose1);
    }
    
    
    public ResponseEntity<?> validarRequisicao(String header, String cpf) {
    	ResponseEntity<?> result = null;
    	try {
			if(!cidadaoService.validarRequisicao(header, cpf)) {
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
