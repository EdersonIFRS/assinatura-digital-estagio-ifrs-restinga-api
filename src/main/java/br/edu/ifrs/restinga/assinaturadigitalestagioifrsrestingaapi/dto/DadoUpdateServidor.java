package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadoUpdateServidor(
        @NotBlank
        String nome,
        @NotBlank
        String cargo,
        @NotBlank
        long curso,
        @NotNull @Valid
        Usuario usuarioSistema) {
}
