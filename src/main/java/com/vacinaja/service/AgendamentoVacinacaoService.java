package com.vacinaja.service;

import com.vacinaja.DTO.AgendamentoVacinacaoDTO;
import com.vacinaja.model.AgendamentoVacinacao;
import com.vacinaja.model.Cidadao;
import java.util.Optional;

public interface AgendamentoVacinacaoService {

    public Optional<AgendamentoVacinacao> getAgendamentoVacinacaoById(Long id);

    public void removerAgendamentoVacinacao(AgendamentoVacinacao agendamentoVacinacao);

    public void salvarAgendamentoVacinacao(AgendamentoVacinacao agendamentoVacinacao);

    public void cadastrarAgendamentoVacinacao(AgendamentoVacinacaoDTO agendamentoVacinacaoDTO, Cidadao cidadao);

    public void atualizarAgendamentoVacinacao(AgendamentoVacinacaoDTO agendamentoVacinacaoDTO, AgendamentoVacinacao agendamentoVacinacao);

}
