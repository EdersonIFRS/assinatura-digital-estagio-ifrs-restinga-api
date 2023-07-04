package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Historico;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;

public record DadosListagemSolicitacaoServidor(Long id, String titulo, String conteudo, String status, String tipo, String etapa, String observacao, LocalDateTime dataSolicitacao, Aluno aluno, List<Historico> historico) {
    public DadosListagemSolicitacaoServidor(SolicitarEstagio solicitarEstagio){
        this(solicitarEstagio.getId(),
                solicitarEstagio.getTitulo(),
            solicitarEstagio.getConteudo(),
            solicitarEstagio.getStatus(),
            solicitarEstagio.getTipo(),
            solicitarEstagio.getEtapa(),
            solicitarEstagio.getObservacao(),
            solicitarEstagio.getDataSolicitacao(),
            solicitarEstagio.getAluno(),
            solicitarEstagio.getHistorico());
    } 
}
