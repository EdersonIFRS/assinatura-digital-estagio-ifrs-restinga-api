package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

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
    private String senha;
    private boolean ativo;

    public Aluno(DadosCadastroAluno dados) {
        this.ativo = true;
        this.nomeCompleto = dados.nomeCompleto();
        this.email = dados.email();
        this.senha = dados.senha();
    }    

    public void atualizarInformacoes(@Valid DadosAtualizacaoAluno dados) {
        if(dados.nomeCompleto() != null){
            this.nomeCompleto = dados.nomeCompleto();
        }

        if(dados.email() != null){
            this.email = dados.email();
        }

        if(dados.senha() != null){
            this.senha = dados.senha();
        }
    }

    public void desativar() {
        this.ativo = false;
    }
    
}