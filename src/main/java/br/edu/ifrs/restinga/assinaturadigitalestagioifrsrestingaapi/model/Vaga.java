package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "vagas")
@Data
@NoArgsConstructor

public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String titulo;
    @NotNull
    private String agencia;
    @NotNull
    private String empresa;
    @NotNull
    private String descricao;
    @NotNull
    private String local;
    @NotNull
    private String valor;
    @NotNull
    private String turno;


    public Vaga(String titulo,String empresa, String agencia, String descricao, String local, String valor, String turno) {
        this.titulo = titulo;
        this.empresa = empresa;
        this.agencia = agencia;
        this.descricao = descricao;
        this.local = local;
        this.valor = valor;
        this.turno = turno;
    }

}