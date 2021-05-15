package com.vacinaja.service;


import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinaja.model.Admin;
import com.vacinaja.model.Cidadao;
import com.vacinaja.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	JwtService jwtService;

	public Optional<Admin> getAdminByCpf(String cpf) {
		return adminRepository.findById(cpf);
	}



	@Override
	public boolean validarRequisicao(String header) throws ServletException {
		String idRequester = getIdRequester(header);
		
		Optional<Admin> optionalAdmin = adminRepository.findById(idRequester);
		
		return optionalAdmin.isPresent() && idRequester!= null;
	}

	private String getIdRequester(String header) throws ServletException {
		return jwtService.getIdbyToken(header);
	}

}
