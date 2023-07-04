package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Historico;

@Repository
public interface HistoricoSolicitacaoRepository extends JpaRepository<Historico, Long>{

    

}
