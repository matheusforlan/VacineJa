package com.vacinaja.model;

import com.vacinaja.model.situacoes.*;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;





@Entity
public class Cidadao {

    @Id
    private String cpf;

    private String nome;

    private Date dataNasc;
    
    private String cartaoSus;

    private String telefone;

    private String email;

    private String profissao;

    private String comorbidades;

    private String senha;

    @Enumerated(EnumType.STRING)
    private EnumSituacoes situacao;

    Cidadao(){}
    
    public Cidadao(String cpf, String nome, Date dataNasc, String cartaoSus, String telefone, String email, String profissao,
    String comorbidades, String senha){
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cartaoSus = cartaoSus;
        this.telefone = telefone;
        this.email = email;
        this.profissao = profissao;
        this.comorbidades = comorbidades;
        this.senha = senha;
        this.situacao = EnumSituacoes.NAO_HABILITADO;
    }



    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCartaoSus() {
        return this.cartaoSus;
    }

    public void setCartaoSus(String cartaoSus) {
        this.cartaoSus = cartaoSus;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfissao() {
        return this.profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getComorbidades() {
        return this.comorbidades;
    }

    public void setComorbidades(String comorbidades) {
        this.comorbidades = comorbidades;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void adicionarComorbidades(String comorbidade){
    	if (comorbidades == null) {
        	this.comorbidades = comorbidade;
        } else {
        	if(this.comorbidades.length() >= 0){
                
            	this.comorbidades += ", ";
            }
            this.comorbidades += comorbidade;
        }
    	
    	
    }
    public void setSituacao(EnumSituacoes situacao){
        this.situacao = situacao;
    }

    public EnumSituacoes getSituacao(){
        return this.situacao;
    }
    
    
           @Override
    public String toString(){
        String retorno = "";
        retorno += "Nome: " + this.nome + "\n" + 
        "CPF: " + this.cpf + "\n" +
        "Cartão SUS: " + this.cartaoSus + "\n" +
        "Data de Nascimento " + this.dataNasc + "\n" +
        "Email: " + this.email + "\n" +
        "Telefone: " + this.telefone + "\n\n" +
        "Situação do cidadão: " + this.situacao.getSituacao().toString() + "\n\n"; 

        return retorno;
    }

}
