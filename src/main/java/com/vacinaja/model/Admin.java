package com.vacinaja.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Admin {
	
	@Id
	String user;
	
	String senha;

	Admin() {}
		
	public Admin (String user, String senha) {
		this.user = user;
		this.senha = senha;
	}

	public String getUser() {
		return user;
	}

	public String getSenha() {
		return senha;
	}
}
