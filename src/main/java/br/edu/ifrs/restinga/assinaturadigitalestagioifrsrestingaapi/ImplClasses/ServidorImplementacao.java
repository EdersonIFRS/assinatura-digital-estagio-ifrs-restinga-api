package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.ImplClasses;

import java.util.List;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller.BaseController;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroServidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosDetalhamentoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.infra.error.TratadorDeErros;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;
import org.springframework.http.HttpStatus;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Servidor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class ServidorImplementacao extends BaseController {
	
	@PersistenceContext	
	EntityManager manager;


	public Servidor findId(long id) {
		// TODO Auto-generated method stub
		return manager.find(Servidor.class, id);
	}

	@Transactional
	public ResponseEntity salvar(DadosCadastroServidor dadosCadastroServidor, UriComponentsBuilder uriBuilder) {

		var servidor = new Servidor(dadosCadastroServidor);

		if(usuarioRepository.findByEmail(dadosCadastroServidor.usuarioSistema().getEmail())!= null){
			return TratadorDeErros.tratarErro409();
		}


		if (!emailValidator.validaEmail(servidor.getUsuarioSistema().getEmail())) {
			return TratadorDeErros.tratarErro400(HttpStatus.BAD_REQUEST);
		}


		var usuarioSistemaServidor = new Usuario(
				dadosCadastroServidor.usuarioSistema().getEmail(),
				passwordEncoder.encode(dadosCadastroServidor.usuarioSistema().getSenha())
		);

		servidor.setUsuarioSistema(usuarioSistemaServidor);

		usuarioRepository.save(servidor.getUsuarioSistema());
		var uri = uriBuilder.path("/servidor/{id}").buildAndExpand(servidor.getId()).toUri();
	    return ResponseEntity.created(uri).body(servidor);
	}


	public List<Servidor> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Servidor", Servidor.class).getResultList();
	}
	
	@Transactional
	public void remover(Servidor servidor) {
		// TODO Auto-generated method stub
		manager.remove(servidor);
	}
	
	

}
