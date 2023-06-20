package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "SolicitarEstagio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitarEstagio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;

    @OneToMany(mappedBy = "solicitarEstagio")
    private List<Documento> documento = new ArrayList<>();
    @OneToOne
    private Aluno aluno;

    private String status;

    @OneToOne Servidor servidor;

    private LocalDateTime dataSolicitacao;

    private String titulo;

    private String conteudo;

    private String etapa;

    private String observacao;  

    public SolicitarEstagio(Aluno aluno, Servidor servidor, String tipo, String titulo, String conteudo, String observacao) {
        this.aluno = aluno;
        this.servidor = servidor;
        this.tipo = tipo;
        this.dataSolicitacao = LocalDateTime.now();
        this.status = "em andamento";
        this.titulo = aluno.getNomeCompleto();
        this.conteudo = "aqui vai ser o conteudo do "+dataSolicitacao;
        this.etapa = "1";
        this.observacao = observacao;
    }
}
