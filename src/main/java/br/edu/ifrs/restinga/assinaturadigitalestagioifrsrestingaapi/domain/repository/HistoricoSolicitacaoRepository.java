package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository;


import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.HistoricoSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoSolicitacaoRepository extends JpaRepository<HistoricoSolicitacao, Long> {
}
