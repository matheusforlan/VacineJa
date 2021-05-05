package com.vacinaja.service;

import com.vacinaja.DTO.AgendamentoVacinacaoDTO;
import com.vacinaja.model.AgendamentoVacinacao;
import com.vacinaja.repository.AgendamentoVacinacaoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoVacinacaoServiceImpl implements AgendamentoVacinacaoService {

    @Autowired
    AgendamentoVacinacaoRepository agendamentoVacinacaoRepository;

    
    @Override
    public Optional<AgendamentoVacinacao> getAgendamentoVacinacaoById(Long id) {
        return agendamentoVacinacaoRepository.findById(id);
    }

    @Override
    public void removerAgendamentoVacinacao(AgendamentoVacinacao agendamentoVacinacao) {
        agendamentoVacinacaoRepository.delete(agendamentoVacinacao);
    }

    @Override
    public void salvarAgendamentoVacinacao(AgendamentoVacinacao agendamentoVacinacao) {
        agendamentoVacinacaoRepository.save(agendamentoVacinacao);
    }

    @Override
    public void cadastrarAgendamentoVacinacao(AgendamentoVacinacaoDTO agendamentoVacinacaoDTO) {
        AgendamentoVacinacao agendamentoVacinacao = new AgendamentoVacinacao(agendamentoVacinacaoDTO.getCidadao(), agendamentoVacinacaoDTO.getData(), agendamentoVacinacaoDTO.getHorario());

        agendamentoVacinacaoRepository.save(agendamentoVacinacao);
    }

    @Override
    public void atualizarAgendamentoVacinacao(AgendamentoVacinacaoDTO agendamentoVacinacaoDTO, AgendamentoVacinacao agendamentoVacinacao) {
        agendamentoVacinacao.setData(agendamentoVacinacaoDTO.getData());
        agendamentoVacinacao.setHorario(agendamentoVacinacaoDTO.getHorario());
    }
}
