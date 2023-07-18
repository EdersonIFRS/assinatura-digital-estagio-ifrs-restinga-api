package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.ImplClasses;

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

    @Transactional
    public void mudarSolicitacao(SolicitarEstagio solicitarEstagio, String situacao){
        Historico log = new Historico(LocalDateTime.now(),solicitarEstagio.getEtapa(),situacao,solicitarEstagio);
        historicoSolicitacaoRepository.save(log);
      
    }
}
