package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;



import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Servidor {
	
	private String nome;
	private String cargo;
	
	@Column
	private String senha;
	private String curso;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	private Long id;
	
	
	@OneToOne
	@JoinColumn(name = "usuario_sistema_id")
	private Usuario usuarioSistema;

	

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	
	

}
