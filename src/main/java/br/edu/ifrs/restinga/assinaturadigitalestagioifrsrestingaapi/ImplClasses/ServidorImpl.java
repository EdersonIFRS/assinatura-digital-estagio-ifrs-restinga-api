package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.ImplClasses;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Servidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.repository.ServidorInterf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;



@Component
public class ServidorImpl implements ServidorInterf  {
	
	@PersistenceContext	
	EntityManager manager;
	
	
	public List<Servidor> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Servidor", Servidor.class).getResultList();
	}
	

	public Servidor buscar(long id) {
		// TODO Auto-generated method stub
		return manager.find(Servidor.class, id);
	}
	
	
	@Transactional
	
	public Servidor salvar(Servidor servidor) {
		// TODO Auto-generated method stub
		return manager.merge(servidor);
	}
	
	@Transactional
	public void remover(Servidor servidor) {
		// TODO Auto-generated method stub
		manager.remove(servidor);
	}
	
	

}
