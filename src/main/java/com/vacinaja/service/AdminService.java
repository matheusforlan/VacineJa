package com.vacinaja.service;

import java.util.Optional;

import javax.servlet.ServletException;

import com.vacinaja.model.*;


public interface AdminService {
	
	public Optional<Admin> getAdminByCpf (String cpf);

	public boolean validarRequisicao(String header) throws ServletException;

}
