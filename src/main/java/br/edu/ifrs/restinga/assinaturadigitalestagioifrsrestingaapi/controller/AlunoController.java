package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;

import java.util.List;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.UsuarioRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.infra.security.TokenService;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.AlunoRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosAtualizacaoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosDetalhamentoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosListagemAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
public class AlunoController {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastrarAluno")
    @Transactional
    public ResponseEntity cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder){
        var aluno = new Aluno(dados);

        if(usuarioRepository.findByEmail(dados.usuarioSistema().getEmail())!= null){
            return ResponseEntity.noContent().build();
        }

        var usuarioSistema = new Usuario(
                dados.usuarioSistema().getEmail(),
                passwordEncoder.encode(dados.usuarioSistema().getSenha())
                );

        aluno.setUsuarioSistema(usuarioSistema);

        usuarioRepository.save(aluno.getUsuarioSistema());
        alunoRepository.save(aluno);
        //spring cria a URI no metodo passa o complemento da url  passa o id do novo aluno .toURI cria objeto URI
        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping("/buscarAlunos")
    public ResponseEntity<List<DadosListagemAluno>> buscarAlunos(){
        var lista = alunoRepository.findAll().stream().map(DadosListagemAluno::new).toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/atualizarAluno")
    @Transactional
    public ResponseEntity atualizarAluno(@RequestBody @Valid DadosAtualizacaoAluno dadosAluno){


        var aluno = alunoRepository.getReferenceById(dadosAluno.id());
//        var tokenJWT="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgQVNTSU5BVFVSQS5FU1QuSUZSUyIsInN1YiI6Ik1heWNvbiIsImV4cCI6MTY4Mzk1MTQ3NH0.b2gUIdazmg4BofbKQTAqOJSgnSe-QxMjXDwvbWjvi88";
//        if(!aluno.getUsuarioSistema().getEmail().equals(tokenService.getSubject(tokenJWT))){
//            return ResponseEntity.noContent().build();
//        }
        aluno.atualizarInformacoes(dadosAluno);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno)); //devolve um DTO
    }

    @DeleteMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var aluno = alunoRepository.getReferenceById(id);
        aluno.desativar();

        return ResponseEntity.noContent().build();
    } 

}
