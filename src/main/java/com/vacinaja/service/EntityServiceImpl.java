package com.vacinaja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinaja.DTO.EntityDTO;
import com.vacinaja.model.Entitys;
import com.vacinaja.repository.EntityRepository;

@Service
public class EntityServiceImpl implements EntityService {

	@Autowired
	private EntityRepository entityRepository;

	@Override
	public List<Entitys> listarEntitys() {
		return entityRepository.findAll();
	}

}
