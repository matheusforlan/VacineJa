package com.vacinaja.controller;

import com.vacinaja.DTO.FuncionarioDTO;
import com.vacinaja.model.AplicacaoVacina;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Funcionario;
import com.vacinaja.model.Lote;
import com.vacinaja.model.Vacina;
import com.vacinaja.model.situacoes.EnumSituacoes;
import com.vacinaja.service.AplicacaoVacinaService;
import com.vacinaja.service.CidadaoService;
import com.vacinaja.service.FuncionarioService;
import com.vacinaja.service.LoteService;
import com.vacinaja.service.VacinaService;
import com.vacinaja.util.ErroAplicacao;
import com.vacinaja.util.ErroCidadao;
import com.vacinaja.util.ErroFuncionario;
import com.vacinaja.util.ErroLote;
import com.vacinaja.util.ErroVacina;
import com.vacinaja.util.MetodosAuxiliares;


import org.joda.time.DateTime;
import java.sql.Date;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
@CrossOrigin
public class FuncionarioApiController {
	
	@Autowired
    FuncionarioService funcionarioService;
	@Autowired
	CidadaoService cidadaoService;
    @Autowired
    VacinaService vacinaService;
    @Autowired
    LoteService loteService;
    @Autowired
    AplicacaoVacinaService aplicacaoService;
	
	// ------------------------------------------ cadastro de funcionário ------------------------------------------
    @RequestMapping(value = "/cadastro-funcionario", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarFuncionario(@RequestBody String cpf, String cargo, String localDeTrabalho){
               
        Optional<Cidadao> optionalCidadao = cidadaoService.getCidadaoByCpf(cpf);
        
        if(!optionalCidadao.isPresent()){
            return ErroCidadao.erroCidadaoNaoEncontrado(cpf);
        }
        
        Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioByCpf(cpf);
        
        if(optionalFuncionario.isPresent()) {
            return ErroFuncionario.erroFuncionarioJaCadastrado(cpf);
        }
        
        Cidadao cidadao = optionalCidadao.get();
        
        //removendo o cidadao com mesmo id, pra n dar conflito
        cidadaoService.removerCidadao(cidadao);
        
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(cpf, cidadao.getNome(), cidadao.getDataNasc(), cidadao.getCartaoSus(), 
        cidadao.getTelefone(), cidadao.getEmail(), cidadao.getProfissao(), cidadao.getComorbidades(), cidadao.getSenha(), cargo, localDeTrabalho);
        
        // Precisa da autorização do administrador do sistema.
        funcionarioService.cadastrarFuncionario(funcionarioDTO);   

        return new ResponseEntity<FuncionarioDTO>(funcionarioDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/ativar-comorbidade", method = RequestMethod.POST)
    public ResponseEntity<?> ativarComorbidade(@RequestBody String comorbidade,
    		@RequestHeader ("Authorization") String header){
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	return null;
    }

    // ------------------------Listar Pessoas não habilitdas que possuem alguma comorbidade X ----------------------------------
    @RequestMapping(value = "/listagem-cidadaos/{comorbidade}", method = RequestMethod.GET)
    public ResponseEntity<?> listarCidadaosComComorbidadesAtivadas(@RequestParam String comorbidade,
    		@RequestHeader ("Authorization") String header){
    	
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
        List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComComorbidade(comorbidade);
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com essa comorbidade na base de dados.", HttpStatus.NOT_FOUND);
    
       }
       return new ResponseEntity<List<Cidadao>>(cidadaos, HttpStatus.OK);
    }
    
    // ------------------------Listar Pessoas não habilitdas que possuem alguma Profissão X ----------------------------------
    @RequestMapping(value = "/listagem-cidadaos/{profissao}", method = RequestMethod.GET)
    public ResponseEntity<?> listarCidadaosComProfissaoAtivada(@RequestParam String profissao,
    	@RequestHeader ("Authorization") String header){
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComProfissao(profissao);
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com essa profissão na base de dados.", HttpStatus.NOT_FOUND);
    
        }
        return new ResponseEntity<List<Cidadao>>(cidadaos, HttpStatus.OK);
    }

    // ------------------------Listar Pessoas não habilitdas que possuem a idade maior ou igual a uma idade X ----------------------------
    @RequestMapping(value = "/listagem-cidadaos/{idade}", method = RequestMethod.GET)
    public ResponseEntity<?> listarCidadaosComIdadeMinima(@RequestParam int idade, 
    		@RequestHeader ("Authorization") String header){
    	
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
        List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComIdadeMinima(idade);
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com idade no mínimo igual a " + idade + ".", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Cidadao>>(cidadaos, HttpStatus.OK);
    }

    // ---------------------------Ativar idade minima -----------------------------------------------------------------------
    @RequestMapping(value = "/habilitar-idade/{idade}", method = RequestMethod.PUT)
    public ResponseEntity<?> habilitarCidadaosComIdadeMinima(@RequestParam int idade, 
    		@RequestHeader ("Authorization") String header ){
    	
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComIdadeMinima(idade);
    	List<String> cidadaosNotificados = new ArrayList<String>();
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com idade no mínimo igual a " + idade + ".", HttpStatus.NOT_FOUND);
        }
        for(Cidadao cidadao : cidadaos){
            cidadao.getSituacao().getSituacao().mudaSituacao(cidadao);
            cidadaoService.salvarCidadao(cidadao);
            cidadaosNotificados.add("SMS e e-mail enviados para " + cidadao.getNome() + " - " + cidadao.getEmail()+
            		" - " + cidadao.getTelefone() + " - para tomar a primeira dose. \n");
        }
        return new ResponseEntity<List<String>>(cidadaosNotificados, HttpStatus.OK);
    }

    // ------------------------Habilitar Pessoas que possuem alguma comorbidade X ----------------------------------
    @RequestMapping(value = "/habilitar-comorbidade/{comorbidade}", method = RequestMethod.PUT)
    public ResponseEntity<?> habilitarCidadaosComComorbidade(@RequestParam String comorbidade,
    		@RequestHeader ("Authorization") String header){
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComComorbidade(comorbidade);
    	List<String> cidadaosNotificados = new ArrayList<String>();
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com essa comorbidade na base de dados.", HttpStatus.NOT_FOUND);
        }
        for(Cidadao cidadao : cidadaos){
            cidadao.getSituacao().getSituacao().mudaSituacao(cidadao);
            cidadaoService.salvarCidadao(cidadao);
            cidadaosNotificados.add("SMS e e-mail enviados para " + cidadao.getNome() + " - " + cidadao.getEmail()+
            		" - " + cidadao.getTelefone() + " - para tomar a primeira dose. \n");
        }
        return new ResponseEntity<List<String>>(cidadaosNotificados, HttpStatus.OK);

    }

    // ------------------------Habilitar Pessoas que possuem alguma Profissoa X ----------------------------------
    @RequestMapping(value = "/habilitar-profissao/{profissao}", method = RequestMethod.PUT)
    public ResponseEntity<?> habilitarCidadaosComProfissao(@RequestParam String profissao,
    		@RequestHeader ("Authorization") String header){
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	List<Cidadao> cidadaos = geraCidadaosNaoHabilitadosComProfissao(profissao);
    	List<String> cidadaosNotificados = new ArrayList<String>();
        if(cidadaos.isEmpty()) {
            return new ResponseEntity<String>("Não foi possível encontrar cidadãos com essa profissão na base de dados.", HttpStatus.NOT_FOUND);
    
        }
        for(Cidadao cidadao : cidadaos){
            cidadao.getSituacao().getSituacao().mudaSituacao(cidadao);
            cidadaoService.salvarCidadao(cidadao);
            cidadaosNotificados.add("SMS e e-mail enviados para " + cidadao.getNome() + " - " + cidadao.getEmail()+
            		" - " + cidadao.getTelefone() + " - para tomar a primeira dose. \n");
        }
        
        return new ResponseEntity<List<String>>(cidadaosNotificados, HttpStatus.OK);
   
    }

    // ------------------------------------------ metodo para listar vacinas com lotes disponiveis ------------------------------------------
    @RequestMapping(value = "/listar-vacinas-disponiveis", method = RequestMethod.GET)
    public ResponseEntity<?> listarVacinasDisponiveis(@RequestHeader ("Authorization") String header) {
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	List<Vacina> vacinas = vacinaService.listarVacinas();

        if (vacinas.isEmpty()) {
            return ErroVacina.erroSemVacinasCadastradas();
        }

        List<Lote> lotes = loteService.listarLotes();

        if (lotes.isEmpty()) {
            return ErroLote.erroSemLotesCadastrados();
        }

        String vacinasDisponiveis = "";

        for (Vacina vacina : vacinas) {

            String retornoVacina = vacina.toString();

            for (Lote lote : lotes) {

                if (lote.getVacinaId()==vacina.getId()) {
                    retornoVacina += "\n * " + lote.toString() + "\n";
                }
            }

            if (!retornoVacina.equals(vacina.toString())) {
                vacinasDisponiveis += retornoVacina;
            }
        }

        if (vacinasDisponiveis.equals("")) {
            vacinasDisponiveis = "Não há vacinas disponíveis no momento...";
        }

        return new ResponseEntity<String>(vacinasDisponiveis, HttpStatus.OK);
    }

    // metodo que olha se os cidadãos ja podem tomar a dose 2
    @RequestMapping(value = "/checar-situação-cidadaos-para-dose2", method = RequestMethod.PUT)
    public ResponseEntity<?> checarCidadaos(@RequestHeader ("Authorization") String header){
        
    	ResponseEntity<?> erroRequisicao = validarRequisicao(header);
    	if (erroRequisicao != null) return  erroRequisicao;
    	
    	List<Cidadao> cidadaos = cidadaoService.getCidadaosBySituacao(EnumSituacoes.TOMOU_DOSE_1);
    	List<String> cidadaosNotificados = new ArrayList<String>();
        if(!cidadaos.isEmpty()){
            for(Cidadao cidadao: cidadaos){
                AplicacaoVacina aplicacaoVacina = aplicacaoService.getById(cidadao.getCpf()).get();

                if(calculaDias(aplicacaoVacina.getVacina(), aplicacaoVacina.getDataAplicacao())) {
                	cidadaosNotificados.add("SMS e e-mail enviados para " + cidadao.getNome() + " - " + cidadao.getEmail()+
                    		" - " + cidadao.getTelefone() + " - para tomar a segunda dose. \n");
                }
            }
        }
        return new ResponseEntity<List<String>>(cidadaosNotificados, HttpStatus.OK);
    }
    
    
    
    
    //--------------------------------------Métodos auxiliares específicos -------------------------------------------------------

     private boolean calculaDias(Vacina vacina, Date dataAplicacao) {
        int diasParaDose2 = vacina.getDiasParaSegundaDose();
        int diasQuePassaram = MetodosAuxiliares.caculaDias(dataAplicacao);
        return diasQuePassaram >= diasParaDose2;
    }

    private List<Cidadao> geraCidadaosNaoHabilitadosComComorbidade(String comorbidade) {
        List<Cidadao> cidadaos = cidadaoService.getCidadaosBySituacao(EnumSituacoes.NAO_HABILITADO);
        
        List<Cidadao> aux = new ArrayList<Cidadao>();
        for(Cidadao cidadao : cidadaos){
            if(cidadao.getComorbidades().contains(comorbidade)){
                aux.add(cidadao);
            }
        }
        return aux;
    }
    
    private List<Cidadao> geraCidadaosNaoHabilitadosComProfissao(String profissao){
        List<Cidadao> cidadaos = cidadaoService.getCidadaosBySituacao(EnumSituacoes.NAO_HABILITADO);
        
        List<Cidadao> aux = new ArrayList<Cidadao>();
        for(Cidadao cidadao : cidadaos){
            if(cidadao.getComorbidades().contains(profissao)){
                aux.add(cidadao);
            }
        }
        return aux;
    }

    private List<Cidadao> geraCidadaosNaoHabilitadosComIdadeMinima(int idade) {
        List<Cidadao> cidadaos = cidadaoService.getCidadaosBySituacao(EnumSituacoes.NAO_HABILITADO);
        
        List<Cidadao> aux = new ArrayList<Cidadao>();

        for(Cidadao cidadao : cidadaos){
            if(MetodosAuxiliares.calculaIdade(cidadao.getDataNasc()) >= idade){
                aux.add(cidadao);
            }
        }
        return aux;
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


