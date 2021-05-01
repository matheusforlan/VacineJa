package com.vacinaja.service;

import java.util.List;
import java.util.Optional;

import com.vacinaja.DTO.VacinaDTO;
import com.vacinaja.model.Vacina;
import com.vacinaja.repository.VacinaRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class VacinaServiceImpl implements VacinaService{

    @Autowired
    VacinaRepository vacinaRepository;

    @Override
    public Optional<Vacina> getVacinaById(Long id) {
        return vacinaRepository.findById(id);
    }

    @Override
    public void removerVacina(Vacina vacina) {
        vacinaRepository.delete(vacina);
    }

    @Override
    public void salvarVacina(Vacina vacina) {
        vacinaRepository.save(vacina);
    }

    @Override
    public List<Vacina> listarVacinas() {
        return vacinaRepository.findAll();
    }

    @Override
    public Vacina cadastrarVacina(VacinaDTO vacinaDTO) {
        Vacina vacina = new Vacina(vacinaDTO.getNome(), vacinaDTO.getFabricante(), vacinaDTO.getDiasParaSegundaDose());

        vacinaRepository.save(vacina);

        return vacina;
    }
    
}
