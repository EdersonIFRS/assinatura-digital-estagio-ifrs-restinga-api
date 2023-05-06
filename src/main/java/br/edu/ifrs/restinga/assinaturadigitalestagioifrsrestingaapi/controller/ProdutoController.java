package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.DadosAtualizacaoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.DadosCadastroAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.DadosListagemAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.repository.AlunoRepository;
import jakarta.transaction.Transactional;

@RestController
public class ProdutoController {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/cadastrarAluno")
    @Transactional
    public String cadastrarAluno(@RequestBody DadosCadastroAluno dadosAluno){
        alunoRepository.save(new Aluno(dadosAluno));
        System.out.println(dadosAluno);
        return "Aluno cadastrado";
    }

    @GetMapping("/buscarAlunos")
    public List<DadosListagemAluno> buscarAlunos(){
        return alunoRepository.findAll().stream().map(DadosListagemAluno::new).toList();
    }

    @PutMapping("/atualizarAluno")
    @Transactional
    public String atualizarAluno(@RequestBody DadosAtualizacaoAluno dadosAluno){
        var aluno = alunoRepository.getReferenceById(dadosAluno.id());
        aluno.atualizarInformacoes(dadosAluno);
        return "Aluno Atualizado";
    }

    @DeleteMapping("/desativar/{id}")
    @Transactional
    public void desativar(@PathVariable Long id){
        var aluno = alunoRepository.getReferenceById(id);
        aluno.desativar();
    } 

}
