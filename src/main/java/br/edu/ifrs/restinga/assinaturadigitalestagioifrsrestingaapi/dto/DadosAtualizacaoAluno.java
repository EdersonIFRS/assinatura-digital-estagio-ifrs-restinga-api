package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Curso;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(

    @NotNull
    Long id,
    String nomeCompleto,
    Usuario usuarioSistema,
    String turno,
    String matricula,
    String ingresso,
    Curso curso
) {
    
}
