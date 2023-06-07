package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Curso;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
        @NotNull
        @Positive
        long curso

) {
    
}
