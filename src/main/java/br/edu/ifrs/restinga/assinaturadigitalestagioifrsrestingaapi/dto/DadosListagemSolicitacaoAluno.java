package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import java.time.LocalDateTime;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;

public record DadosListagemSolicitacaoAluno(String titulo, String conteudo, String status, String etapa, String observacao, LocalDateTime dataSolicitacao) {

    public DadosListagemSolicitacaoAluno(SolicitarEstagio solicitarEstagio){
        this(solicitarEstagio.getTitulo(),solicitarEstagio.getConteudo(),solicitarEstagio.getStatus(),solicitarEstagio.getEtapa(),solicitarEstagio.getObservacao(), solicitarEstagio.getDataSolicitacao());
    } 
    
}