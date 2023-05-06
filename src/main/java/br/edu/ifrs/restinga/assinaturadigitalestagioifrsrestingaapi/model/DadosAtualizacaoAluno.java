package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(

    @NotNull
    Long id,

    String nomeCompleto,
    
    String email,

    String senha
) {
    
}
