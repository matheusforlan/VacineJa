package com.vacinaja.service;


import java.util.List;
import java.util.Optional;

import com.vacinaja.DTO.FuncionarioDTO;
import com.vacinaja.model.Funcionario;

public interface FuncionarioService {
	
	public Optional<Funcionario> getFuncionarioByCpf(String cpf);
	
    public void salvarFuncionario(Funcionario funcionario);

    public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO);

	public List<Funcionario> listarFuncionariosNaoAprovados();

}
