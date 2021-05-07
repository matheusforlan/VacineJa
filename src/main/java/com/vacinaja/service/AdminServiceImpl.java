package com.vacinaja.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinaja.model.Admin;
import com.vacinaja.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepository;

	public Optional<Admin> getAdminByCpf(String cpf) {
		return adminRepository.findById(cpf);
	}

}