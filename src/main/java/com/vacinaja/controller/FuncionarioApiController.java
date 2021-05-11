package com.vacinaja.controller;

import com.vacinaja.DTO.FuncionarioDTO;
import com.vacinaja.model.Cidadao;
import com.vacinaja.model.Funcionario;
import com.vacinaja.model.Lote;
import com.vacinaja.model.Vacina;
import com.vacinaja.service.CidadaoService;
import com.vacinaja.service.FuncionarioService;
import com.vacinaja.service.LoteService;
import com.vacinaja.service.VacinaService;
import com.vacinaja.util.ErroCidadao;
import com.vacinaja.util.ErroFuncionario;
import com.vacinaja.util.ErroLote;
import com.vacinaja.util.ErroVacina;
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


    // ------------------------------------------ metodo para listar vacinas com lotes disponiveis ------------------------------------------
    @RequestMapping(value = "/listar-vacinas-disponiveis", method = RequestMethod.GET)
    public ResponseEntity<?> listarVacinasDisponiveis() {
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

                if (lote.getVacina().equals(vacina)) {
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

}
