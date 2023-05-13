package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAluno(

        @NotBlank
    String nomeCompleto,
    @NotNull @Valid
    Usuario usuarioSistema,
        @NotBlank
    String turno,
        @NotBlank
    String matricula,
        @NotBlank
    String ingresso,
        @NotBlank
    String curso

) {
    
}
