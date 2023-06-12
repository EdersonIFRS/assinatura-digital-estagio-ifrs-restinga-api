package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record DadosCadastroSolicitacao(
        @NotBlank
        String tipo,
        @NotBlank
        long alunoId,
        @NotBlank
        long cursoId
) {
}
