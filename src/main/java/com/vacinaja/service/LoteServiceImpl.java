package com.vacinaja.service;

import com.vacinaja.DTO.LoteDTO;
import com.vacinaja.model.Lote;
import com.vacinaja.repository.LoteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoteServiceImpl implements LoteService{

    @Autowired
    LoteRepository loteRepository;

    @Override
    public Optional<Lote> getLoteById(Long id) {
        return loteRepository.findById(id);
    }

    @Override
    public void removerLote(Lote lote) {
        loteRepository.delete(lote);
    }

    @Override
    public void salvarLote(Lote lote) {
        loteRepository.save(lote);
    }

    @Override
    public List<Lote> listarLotes() {
        return loteRepository.findAll();
    }

    @Override
    public Lote cadastrarLote(LoteDTO loteDTO) {
        Lote lote = new Lote(loteDTO.getVacina(), loteDTO.getQuantidadeDoses(), loteDTO.getDataValidade());

        loteRepository.save(lote);

        return lote;
    }
}