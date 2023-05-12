package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosAtualizacaoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroAluno;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Aluno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String email;
    private String turno;
    private String matricula;
    private String ingresso;
    private String curso;
    private String senha;
    private boolean ativo;

    public Aluno(DadosCadastroAluno dados) {
        this.ativo = true;
        this.nomeCompleto = dados.nomeCompleto();
        this.email = dados.email();
        this.turno = dados.turno();
        this.matricula = dados.matricula();
        this.ingresso = dados.ingresso();
        this.curso = dados.curso();
        this.senha = dados.senha();
    }    

    public void atualizarInformacoes(@Valid DadosAtualizacaoAluno dados) {
        if(dados.nomeCompleto() != null){
            this.nomeCompleto = dados.nomeCompleto();
        }

        if(dados.email() != null){
            this.email = dados.email();
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

        if(dados.senha() != null){
            this.senha = dados.senha();
        }
    }

    public void desativar() {
        this.ativo = false;
    }
    
}