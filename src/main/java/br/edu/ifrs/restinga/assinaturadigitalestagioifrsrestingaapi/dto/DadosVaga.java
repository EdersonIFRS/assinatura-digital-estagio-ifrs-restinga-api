package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Vaga;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record DadosVaga (
        @Nullable
        long id,
        @NotNull
         String titulo,
        @NotNull
         String empresa,
        @NotNull
         String agencia,
        @NotNull
         String descricao,
        @NotNull
         String local,
        String valor,
        @NotNull
        String turno   
){

    public DadosVaga(Vaga vaga) {
       this(vaga.getId(), vaga.getTitulo(), vaga.getEmpresa(), vaga.getAgencia(), vaga.getDescricao(), vaga.getLocal(), vaga.getValor(), vaga.getTurno());
    }
}