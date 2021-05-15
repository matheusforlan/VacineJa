package com.vacinaja.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinaja.DTO.CidadaoDTO;
import com.vacinaja.DTO.FuncionarioDTO;
import com.vacinaja.model.Admin;
import com.vacinaja.model.Funcionario;
import com.vacinaja.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private JwtService jwtService;

	@Override
	public void salvarFuncionario(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}

	@Override
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = new Funcionario(funcionarioDTO);
		funcionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> getFuncionarioByCpf(String cpf) {		
		return funcionarioRepository.findById(cpf);
	}
	
	@Override
	public List<Funcionario> listarFuncionariosNaoAprovados() {
		return funcionarioRepository.findAllByIsAprovadoFalse();
	}

	@Override
	public void removerFuncionario(String cpf) {
		 Funcionario funcionario = funcionarioRepository.findById(cpf).get();
		 funcionarioRepository.delete(funcionario);
		 
		
		
	}

	@Override
	public boolean validarRequisicao(String header) throws ServletException {
		String idRequester =jwtService.getIdbyToken(header);
		
		Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(idRequester);
		
		if (!optionalFuncionario.isPresent()) {
			return false;
		}
		Funcionario funcionario = optionalFuncionario.get();
		
		return funcionario.isAprovado();
	}
	@Override
	public String getIdRequester(String header) throws ServletException {
		return jwtService.getIdbyToken(header);
	}

}
