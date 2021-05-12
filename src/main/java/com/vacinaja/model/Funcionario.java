package com.vacinaja.model;

import java.sql.Date;

import javax.persistence.Entity;

import com.vacinaja.DTO.FuncionarioDTO;

@Entity
public class Funcionario extends Cidadao{
	
	private String cargo;
	private String localDeTrabalho;
	boolean isAprovado;
	
	Funcionario() {}

	public Funcionario(String cargo, String localDeTrabalho) {
		this.cargo = cargo;
		this.localDeTrabalho = localDeTrabalho;
	}
	

	public Funcionario(String cpf, String nome, Date dataNasc, String cartaoSus, String telefone, String email,
			String profissao, String comorbidades, String senha) {
		super(cpf, nome, dataNasc, cartaoSus, telefone, email, profissao, comorbidades,
			senha);
		
	}
	
	
	//  criei esse construtor com DTO, pra poder coloar a primary key(cpf)
	public Funcionario(FuncionarioDTO funcionarioDTO) {
		super(funcionarioDTO.getCpf(), funcionarioDTO.getNome(),funcionarioDTO.getDataNasc(),
				funcionarioDTO.getCartaoSus(), funcionarioDTO.getTelefone(), funcionarioDTO.getEmail(),
				funcionarioDTO.getProfissao(), funcionarioDTO.getComorbidades(),
				funcionarioDTO.getSenha());
		this.cargo = funcionarioDTO.getCargo();
		this.localDeTrabalho = funcionarioDTO.getLocalDeTrabalho();
		this.isAprovado = false;
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
	
	public boolean isAprovado() {
		return isAprovado;
	}

	public void setAprovado(boolean isAprovado) {
		this.isAprovado = isAprovado;
	}

	@Override
	public String toString() {
		return "Funcionário:" + super.getNome() + " cargo:" + this.cargo + ", localDeTrabalho:" + this.localDeTrabalho ;
	}
	

	
	
}
