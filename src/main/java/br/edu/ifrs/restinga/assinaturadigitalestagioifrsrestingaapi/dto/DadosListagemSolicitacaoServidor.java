package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import java.time.LocalDateTime;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;

public record DadosListagemSolicitacaoServidor(String titulo, String conteudo, String status, String etapa, String observacao, LocalDateTime dataSolicitacao, Aluno aluno) {
    public DadosListagemSolicitacaoServidor(SolicitarEstagio solicitarEstagio){
        this(solicitarEstagio.getTitulo(),solicitarEstagio.getConteudo(),solicitarEstagio.getStatus(),solicitarEstagio.getEtapa(),solicitarEstagio.getObservacao(), solicitarEstagio.getDataSolicitacao(), solicitarEstagio.getAluno());
    } 
    
}
