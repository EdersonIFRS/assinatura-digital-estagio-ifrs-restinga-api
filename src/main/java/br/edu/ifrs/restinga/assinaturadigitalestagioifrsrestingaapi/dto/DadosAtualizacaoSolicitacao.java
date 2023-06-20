package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoSolicitacao   (

        @NotBlank
        String status,
        @NotBlank
        String etapa,

        @NotBlank
        String resposta
        )
{

}