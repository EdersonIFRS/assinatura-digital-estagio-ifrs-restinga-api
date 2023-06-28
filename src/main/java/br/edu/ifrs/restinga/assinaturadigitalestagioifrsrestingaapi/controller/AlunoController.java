package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;

import java.util.List;
import java.util.Optional;


import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.CursoRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.infra.error.TratadorDeErros;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Curso;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Role;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosAtualizacaoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosDetalhamentoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosListagemAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

@RestController
public class AlunoController extends BaseController{
    @Autowired
    CursoRepository cursoRepository;


    @PostMapping("/cadastrarAluno")
    @Transactional
    public ResponseEntity cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder){
        //buscando curso por ID para salvar no aluno pelo construtor.
        Optional<Curso> curso = cursoRepository.findById(dados.curso());

        Optional<Role> role = roleRepository.findById(1L);
        var aluno = new Aluno(dados,curso.get(),role.get());

        if(usuarioRepository.findByEmail(dados.usuarioSistema().getEmail())!= null){
            return TratadorDeErros.tratarErro409("email");
        }

        if (!emailValidator.validaEmail(aluno.getUsuarioSistema().getEmail())) {
            return TratadorDeErros.tratarErro400(HttpStatus.BAD_REQUEST);
        }
        var usuarioSistema = new Usuario(
                dados.usuarioSistema().getEmail(),
                passwordEncoder.encode(dados.usuarioSistema().getSenha()),
                aluno.getRole()
                );


        aluno.setUsuarioSistema(usuarioSistema);


        usuarioRepository.save(aluno.getUsuarioSistema());
        alunoRepository.save(aluno);
        //spring cria a URI no metodo passa o complemento da url  passa o id do novo aluno .toURI cria objeto URI
        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping("alunos/buscarAlunos")
    public ResponseEntity<List<DadosListagemAluno>> buscarAlunos(){
        var lista = alunoRepository.findAll().stream().map(DadosListagemAluno::new).toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/atualizarAluno")
    @Transactional
    public ResponseEntity atualizarAluno(@RequestBody @Valid DadosAtualizacaoAluno dadosAluno, @RequestHeader ("Authorization") String token){
        
        var email = tokenService.getSubject(token.replace("Bearer ", ""));

        var aluno = alunoRepository.getReferenceById(dadosAluno.id());
       
        if(!aluno.getUsuarioSistema().getEmail().equals(email)){
            return TratadorDeErros.tratarErro403();
        }

        aluno.atualizarInformacoes(dadosAluno);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno)); //devolve um DTO
    }

    @GetMapping("/getAluno")
    public ResponseEntity pegarAluno(@RequestHeader("Authorization") String token){

        String email = tokenService.getSubject(token.replace("Bearer ", ""));
        Aluno aluno = alunoRepository.findByUsuarioSistemaEmail(email);

        if(alunoRepository.findById(aluno.getId()).isPresent()){
            return ResponseEntity.ok(alunoRepository.findById(aluno.getId()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var aluno = alunoRepository.getReferenceById(id);
        aluno.desativar();

        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/buscarAlunoPorIdSolicitacao/{idSolicitacao}")
    @ResponseBody
    public ResponseEntity listarIdSolicitacao(@PathVariable long idSolicitacao){
        var aluno =alunoRepository.findById(idSolicitacao);
         
        return ResponseEntity.ok(aluno);
    }

}
