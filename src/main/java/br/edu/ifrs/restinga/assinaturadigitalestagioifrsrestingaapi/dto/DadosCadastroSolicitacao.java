package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record DadosCadastroSolicitacao(
        @NotBlank
        String tipo,
        @NotBlank
        long alunoId,
        @NotBlank
        long cursoId,
        @NotBlank
        String titulo,
        
        @Nullable
        String conteudo,
        @Nullable
        String observacao,

        @Nullable
        String status,
        @Nullable
        String etapa,
        @Nullable
        String respostas

) {
}
