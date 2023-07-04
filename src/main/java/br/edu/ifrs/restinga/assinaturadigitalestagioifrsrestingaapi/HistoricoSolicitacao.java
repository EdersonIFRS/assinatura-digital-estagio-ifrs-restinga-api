package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.HistoricoSolicitacaoRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Historico;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import jakarta.transaction.Transactional;

@Service
public class HistoricoSolicitacao {

    @Autowired
    HistoricoSolicitacaoRepository historicoSolicitacaoRepository;

    @PostPersist
    @PostUpdate
    @PreRemove
    @Transactional
    public void mudarSolicitacao(SolicitarEstagio solicitarEstagio){
        Historico log = new Historico(LocalDateTime.now(),solicitarEstagio.getEtapa(),solicitarEstagio.getStatus(),solicitarEstagio);
        System.out.println("DEBUG:  PASSOU NO HISTORICO SOLICITACAO" );

        historicoSolicitacaoRepository.save(log);
      
    }

    
}
