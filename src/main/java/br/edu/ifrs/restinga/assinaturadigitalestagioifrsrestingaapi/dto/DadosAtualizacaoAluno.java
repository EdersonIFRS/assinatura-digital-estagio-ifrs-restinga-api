package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(

    @NotNull
    Long id,

    String nomeCompleto,
    
    String email,
    String turno,
    String matricula,
    String ingresso,
    String curso,
    String senha
) {
    
}
