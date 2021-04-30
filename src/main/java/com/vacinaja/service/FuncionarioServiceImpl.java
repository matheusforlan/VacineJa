package com.vacinaja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinaja.DTO.FuncionarioDTO;
import com.vacinaja.model.Funcionario;
import com.vacinaja.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Override
	public void salvarFuncionario(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		
	}

	@Override
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = new Funcionario(funcionarioDTO.getCargo(), 
				funcionarioDTO.getLocalDeTrabalho());
		funcionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> getFuncionarioByCpf(String cpf) {		
		return funcionarioRepository.findById(cpf);
	}
}
