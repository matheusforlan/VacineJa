package com.vacinaja.service;

import java.util.Optional;

import com.vacinaja.model.*;


public interface AdminService {
	
	public Optional<Admin> getAdminByCpf (String cpf);

}
