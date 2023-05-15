package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;



import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroAluno;
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
	private String curso;
	


	@OneToOne
	@JoinColumn(name = "usuario_sistema_id")
	private Usuario usuarioSistema;

	public Servidor(DadosCadastroServidor dados) {
		this.nome = dados.nome();
		this.cargo = dados.cargo();
		this.usuarioSistema = dados.usuarioSistema();
		this.curso = dados.curso();

	}

}
