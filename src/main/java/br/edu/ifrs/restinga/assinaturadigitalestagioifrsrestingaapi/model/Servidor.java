package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;



import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadoUpdateServidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroServidor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "servidores" )
@Entity(name = "Servidor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String cargo;

	@OneToOne
	private Curso curso;
	


	@OneToOne
	@JoinColumn(name = "usuario_sistema_id")
	private Usuario usuarioSistema;

	public Servidor(DadosCadastroServidor dados, Curso curso) {
		this.nome = dados.nome();
		this.cargo = dados.cargo();
		this.usuarioSistema = dados.usuarioSistema();
		this.curso = curso;

	}


	public void atualizarInformacoes(DadoUpdateServidor dados, Curso curso) {
		if(dados.nome() != null){
			this.nome = dados.nome();
		}

		if(dados.usuarioSistema() != null){
			this.usuarioSistema.atualizarInformacoes(dados.usuarioSistema());
		}

		if(dados.cargo() != null){
			this.cargo = dados.cargo();
		}

		if(curso != null){
			this.curso = curso;
		}

	}

}
