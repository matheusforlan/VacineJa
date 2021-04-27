package com.vacinaja.controller;

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

import com.vacinaja.DTO.EntityDTO;
import com.vacinaja.model.Entitys;
import com.vacinaja.service.EntityService;
import com.vacinaja.util.ErroEntity;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EntityApiController {

	@Autowired
	EntityService entityService;
	
	@RequestMapping(value = "/entitys", method = RequestMethod.GET)
	public ResponseEntity<?> listarEntitys() {
		
		List<Entitys> entitys = entityService.listarEntitys();
		
		if (entitys.isEmpty()) {
			return ErroEntity.erroSemEntitysCadastrados();
		}
		
		return new ResponseEntity<List<Entitys>>(entitys, HttpStatus.OK);
	}
}