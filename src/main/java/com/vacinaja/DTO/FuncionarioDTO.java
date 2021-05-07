package com.vacinaja.DTO;

import java.sql.Date;

public class FuncionarioDTO extends CidadaoDTO{
	
	private String cargo;
	private String localDeTrabalho;
	private boolean isAprovado;
	


	public FuncionarioDTO(String cpf, String nome, Date dataNasc, String cartaoSus, String telefone, String email,
			String profissao, String comorbidades, String senha, String cargo, String localDeTrabalho) {
		super(cpf, nome, dataNasc, cartaoSus, telefone, email, profissao, comorbidades, senha);
		this.cargo = cargo;
		this.localDeTrabalho = localDeTrabalho;
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


}
