package com.vacinaja.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Funcionario extends Cidadao{
	
	private String cargo;
	private String localDeTrabalho;
	
	Funcionario(){}

	public Funcionario(String cargo, String localDeTrabalho) {
		super();
		this.cargo = cargo;
		this.localDeTrabalho = localDeTrabalho;
	}
	

	public Funcionario(String cpf, String nome, Date dataNasc, String cartaoSus, String telefone, String email,
			String profissao, String comorbidades, String senha) {
		super(cpf, nome, dataNasc, cartaoSus, telefone, email, profissao, comorbidades,
			senha);
		
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLocalDeTrabalho() {
		return localDeTrabalho;
	}

	public void setLocalDeTrabalho(String localDeTrabalho) {
		this.localDeTrabalho = localDeTrabalho;
	}

	@Override
	public String toString() {
		return "Funcionário:" + super.getNome() + " cargo:" + this.cargo + ", localDeTrabalho:" + this.localDeTrabalho ;
	}

	
	
	
	

}
