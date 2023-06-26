package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "historico_solicitacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoSolicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_solicitacao;

    private String email;

    private LocalDateTime data;

    private int etapa;

    public HistoricoSolicitacao(long id, String email, int etapa) {
        this.id_solicitacao = id;
        this.email = email;
        this.data = LocalDateTime.now();
        this.etapa = etapa;
    }
}
