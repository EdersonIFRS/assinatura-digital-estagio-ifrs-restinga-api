package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;


import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.AlunoRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.ServidorRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.SolicitacaoRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroSolicitacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Documento;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Servidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class SolicitacaoController {
    @Autowired
    SolicitacaoRepository solicitacaoRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    ServidorRepository servidorRepository;


    @PostMapping(value = "/cadastrarSolicitacao")
    @Transactional
    public ResponseEntity cadastrarSolicitacao(@RequestBody DadosCadastroSolicitacao dados){

        Optional<Aluno> aluno = alunoRepository.findById(dados.alunoId());
        Optional<Servidor> servidor = servidorRepository.findById(dados.servidorId());
        SolicitarEstagio solicitarEstagio = new SolicitarEstagio(aluno.get(), servidor.get(),dados.tipo());
        solicitacaoRepository.save(solicitarEstagio);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listarDocumentos")
    @ResponseBody
    public String listar(@RequestParam long id){
        Optional<SolicitarEstagio> sol = solicitacaoRepository.findById(id);
        return sol.get().getDocumento().get(1).getNome();
    }

}
