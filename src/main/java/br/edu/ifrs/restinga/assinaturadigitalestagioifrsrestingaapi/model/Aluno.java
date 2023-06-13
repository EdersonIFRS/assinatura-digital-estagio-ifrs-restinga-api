package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosAtualizacaoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroAluno;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity(name = "Aluno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;

    @OneToOne
    @JoinColumn(name = "usuario_sistema_id")
    private Usuario usuarioSistema;

    private String turno;
    private String matricula;
    private String ingresso;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    private Curso curso;

    private boolean ativo;

    public Aluno(DadosCadastroAluno dados, Curso curso, Role role) {
        this.ativo = true;
        this.nomeCompleto = dados.nomeCompleto();
        this.usuarioSistema = dados.usuarioSistema();
        this.turno = dados.turno();
        this.matricula = dados.matricula();
        this.ingresso = dados.ingresso();
        this.curso = curso;
        this.role = role;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoAluno dados) {
        if(dados.nomeCompleto() != null){
            this.nomeCompleto = dados.nomeCompleto();
        }

        if(dados.usuarioSistema() != null){
            this.usuarioSistema.atualizarInformacoes(dados.usuarioSistema());
        }

        if(dados.turno() != null){
            this.turno = dados.turno();
        }

        if(dados.matricula() != null){
            this.matricula = dados.matricula();
        }

        if(dados.ingresso() != null){
            this.ingresso = dados.ingresso();
        }

        if(dados.curso() != null){
            this.curso = dados.curso();
        }

    }

    public void desativar() {
        this.ativo = false;
    }
    
}