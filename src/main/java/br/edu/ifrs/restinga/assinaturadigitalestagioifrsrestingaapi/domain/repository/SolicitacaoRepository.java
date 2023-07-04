package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Servidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRepository  extends JpaRepository<SolicitarEstagio,Long> {
    List<SolicitarEstagio> findByAluno(Aluno aluno);
    List<SolicitarEstagio> findByServidor(Servidor servidor);
    List<SolicitarEstagio> findByServidorAndEtapaIsGreaterThanEqual(Servidor servidor, String etapa);
}
