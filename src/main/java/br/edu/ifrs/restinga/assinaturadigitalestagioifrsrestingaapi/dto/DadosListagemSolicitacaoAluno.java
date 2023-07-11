package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Historico;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;

public record DadosListagemSolicitacaoAluno(String titulo, String conteudo, String status,String tipo, String etapa, String observacao, LocalDateTime dataSolicitacao, List<Historico> historico) {

    public DadosListagemSolicitacaoAluno(SolicitarEstagio solicitarEstagio){
        this(solicitarEstagio.getTitulo(),solicitarEstagio.getConteudo(),solicitarEstagio.getStatus(),solicitarEstagio.getTipo(),solicitarEstagio.getEtapa(),solicitarEstagio.getObservacao(), solicitarEstagio.getDataSolicitacao(), solicitarEstagio.getHistorico());
    } 
    
}