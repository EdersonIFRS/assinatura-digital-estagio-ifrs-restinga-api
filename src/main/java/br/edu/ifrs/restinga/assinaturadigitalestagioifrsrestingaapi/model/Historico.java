package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "HistoricoSolicitacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name= "solicitar_estagio_id")
    @JsonBackReference
    private SolicitarEstagio solicitarEstagio;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime data_solicitacao;

    private String etapa;

    private String status;

    public Historico(LocalDateTime data_solicitacao, String etapa, String status, SolicitarEstagio solicitacao) {
        this.data_solicitacao = data_solicitacao;
        this.etapa = etapa;
        this.status = status;
        this.solicitarEstagio = solicitacao;
    }

    
    
}
