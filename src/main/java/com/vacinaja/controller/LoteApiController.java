package com.vacinaja.controller;

import com.vacinaja.DTO.LoteDTO;
import com.vacinaja.model.Lote;
import com.vacinaja.service.LoteService;
import com.vacinaja.util.ErroLote;
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
public class LoteApiController {

    @Autowired
    LoteService loteService;

    // ------------------------------------------ cadastro de lotes de vacina no sistema ------------------------------------------
    @RequestMapping(value = "/cadastro-lote", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarLote(@RequestBody LoteDTO loteDTO, UriComponentsBuilder ucBuilder) {
        Optional<Lote> optionalLote = loteService.getLoteById(loteDTO.getId());

        if (optionalLote.isPresent()) {
            return ErroLote.erroIdInvalido(loteDTO.getId());
        }

        loteService.cadastrarLote(loteDTO);

        return new ResponseEntity<LoteDTO>(loteDTO, HttpStatus.OK);
    }


    // ------------------------------------------ metodo para listar todos os lotes cadastrados no sistema ------------------------------------------
    @RequestMapping(value = "/lotes", method = RequestMethod.GET)
    public ResponseEntity<?> listarLotes() {
        List<Lote> lotes = loteService.listarLotes();

        if (lotes.isEmpty()) {
            return ErroLote.erroSemLotesCadastrados();
        }

        return new ResponseEntity<List<Lote>>(lotes, HttpStatus.OK);
    }
    
}
