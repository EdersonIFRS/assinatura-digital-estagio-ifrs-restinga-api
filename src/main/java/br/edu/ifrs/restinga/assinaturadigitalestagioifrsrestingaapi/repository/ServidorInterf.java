package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Servidor;


@Repository
public interface ServidorInterf {

	List<Servidor> listar();
	Servidor buscar(long id);
	Servidor salvar(Servidor servidor);
	void remover(Servidor servidor);
}
