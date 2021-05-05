package com.vacinaja.controller;

import com.vacinaja.DTO.VacinaDTO;
import com.vacinaja.model.Vacina;
import com.vacinaja.service.VacinaService;
import com.vacinaja.util.ErroVacina;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class VacinaApiController {

    @Autowired
    VacinaService vacinaService;

    // ------------------------------------------ cadastro de vacina no sistema ------------------------------------------
    @RequestMapping(value = "/cadastro-vacina", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarVacina(@RequestBody VacinaDTO vacinaDTO, UriComponentsBuilder ucBuilder) {
        Optional<Vacina> vacina = vacinaService.getVacinaById(vacinaDTO.getId());

        if (!vacina.isPresent()) {
            return ErroVacina.erroVacinaJaCadastrada(vacinaDTO.getId());
        }

        vacinaService.cadastrarVacina(vacinaDTO);


        return new ResponseEntity<VacinaDTO>(vacinaDTO, HttpStatus.OK);
    }

    // ------------------------------------------ metodos de atualizacao de dados da vacina ------------------------------------------
    
    // alterar nome da vacina
    @RequestMapping(value = "/{id}/alterarNome", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarNome(@PathVariable("id") Long id, @RequestBody String nome) {
        Optional<Vacina> optionalVacina = vacinaService.getVacinaById(id);

        if (!optionalVacina.isPresent()) {
            return ErroVacina.erroVacinaNaoEncontrada(id);
        }

        Vacina vacina = optionalVacina.get();
        vacina.setNome(nome);

        vacinaService.salvarVacina(vacina);

        return new ResponseEntity<Vacina>(vacina, HttpStatus.OK);
    }

    // alterar fabricante da vacina
    @RequestMapping(value = "/{id}/alterarFabricante", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarFabricante(@PathVariable("id") Long id, @RequestBody String fabricante) {
        Optional<Vacina> optionalVacina = vacinaService.getVacinaById(id);

        if (!optionalVacina.isPresent()) {
            return ErroVacina.erroVacinaNaoEncontrada(id);
        }

        Vacina vacina = optionalVacina.get();
        vacina.setFabricante(fabricante);

        vacinaService.salvarVacina(vacina);

        return new ResponseEntity<Vacina>(vacina, HttpStatus.OK);
    }

    // alterar a quantidade de dias para tomar a segunda dose, caso haja duas doses
    @RequestMapping(value = "/{id}/alterarDiasParaSegundaDose", method = RequestMethod.PUT)
    public ResponseEntity<?> alterarDiasParaSegundaDose(@PathVariable("id") Long id, @RequestBody BigInteger diasParaSegundaDose) {
        Optional<Vacina> optionalVacina = vacinaService.getVacinaById(id);

        if (!optionalVacina.isPresent()) {
            return ErroVacina.erroVacinaNaoEncontrada(id);
        }

        Vacina vacina = optionalVacina.get();
        vacina.setDiasParaSegundaDose(diasParaSegundaDose);

        vacinaService.salvarVacina(vacina);

        return new ResponseEntity<Vacina>(vacina, HttpStatus.OK);
    }


    // ------------------------------------------ metodo para listar todos as vacinas cadastradas no sistema ------------------------------------------
    @RequestMapping(value = "/vacinas", method = RequestMethod.GET)
    public ResponseEntity<?> listarVacinas() {
        List<Vacina> vacinas = vacinaService.listarVacinas();

        if (vacinas.isEmpty()) {
            return ErroVacina.erroSemVacinasCadastradas();
        }

        return new ResponseEntity<List<Vacina>>(vacinas, HttpStatus.OK);
    }
}
