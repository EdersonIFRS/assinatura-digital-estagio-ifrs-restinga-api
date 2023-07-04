package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.HistoricoSolicitacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.ImplClasses.FileImp;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.HistoricoSolicitacaoRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.ServidorRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosAtualizacaoSolicitacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroSolicitacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosListagemSolicitacaoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosListagemSolicitacaoServidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Documento;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Historico;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Servidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class SolicitacaoController extends BaseController{
    @Autowired
    FileImp fileImp;

    @Autowired
    private HistoricoSolicitacao historicoSolicitacao;

    @Autowired 
    private HistoricoSolicitacaoRepository historicoSolicitacaoRepository;

    @PostMapping(value = "/cadastrarSolicitacao")
    @Transactional
    public ResponseEntity cadastrarSolicitacao(@RequestPart("dados") DadosCadastroSolicitacao dados,
                                               @RequestParam("file") List<MultipartFile> file){

        if (servidorRepository.existsServidorByCurso_IdEquals(dados.cursoId())) {
           Optional<Servidor> servidor = servidorRepository.findServidorByCurso_Id(dados.cursoId());
        System.out.println("Dados slt: " + dados.alunoId());
        System.out.println("Dados slt: " + dados.cursoId());
        System.out.println("Dados slt: " + dados.tipo());
        System.out.println("Dados file: " + file.size());
        System.out.println("Dados slt: " + dados.status());
        Optional<Aluno> aluno = alunoRepository.findById(dados.alunoId());

        SolicitarEstagio solicitarEstagio = new SolicitarEstagio(aluno.get(), servidor.get(),dados.tipo(), dados.titulo(), dados.conteudo(), dados.observacao(), "Em Andamento", "2", "");
        fileImp.SaveDocBlob(file,solicitarEstagio);
        solicitacaoRepository.save(solicitarEstagio);
        historicoSolicitacao.mudarSolicitacao(solicitarEstagio);
        return ResponseEntity.ok().build();
    }
    else{
        return ResponseEntity.notFound().build();
    }
    }

    @GetMapping("/listarDocumentos")
    @ResponseBody
    public String listar(@RequestParam long id){
        Optional<SolicitarEstagio> solicitacao = solicitacaoRepository.findById(id);
        return solicitacao.get().getDocumento().get(1).getNome();
    }

    @GetMapping("/dadosSolicitacaoAluno")
    
    public ResponseEntity<List<DadosListagemSolicitacaoAluno>> obterSolicitacoes(@RequestHeader("Authorization") String token) {
        String email = tokenService.getSubject(token.replace("Bearer ", ""));
        Aluno aluno = alunoRepository.findByUsuarioSistemaEmail(email);

        List<SolicitarEstagio> solicitacoes = solicitacaoRepository.findByAluno(aluno);
        List<DadosListagemSolicitacaoAluno> dadosSolicitacoes = solicitacoes.stream()
                .map(DadosListagemSolicitacaoAluno::new)
                .toList();

        return ResponseEntity.ok(dadosSolicitacoes);
    }

    
    @GetMapping("/listarSolicitacoes")
    public ResponseEntity<List<SolicitarEstagio>> listarSolicitacoes() {
        var solicitacoes = solicitacaoRepository.findAll();
        if (solicitacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(solicitacoes);
        }
    }
    


    
    @GetMapping("/listarSolicitacoesPorServidor/{servidorId}")
    public ResponseEntity<List<SolicitarEstagio>> listarSolicitacoesPorServidor(@PathVariable("servidorId") Long servidorId) {
        var servidor = servidorRepository.findById(servidorId);
        if (servidor.isPresent()) {
            var solicitacoesPorServidor = solicitacaoRepository.findByServidor(servidor.get());
            if (solicitacoesPorServidor.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(solicitacoesPorServidor);
            }
        }
            return ResponseEntity.notFound().build();

    }
        
    //teste
  @GetMapping("/dadosSolicitacaoTeste")
public ResponseEntity<List<SolicitarEstagio>> dadoSolicitacaoTeste() {
    List<SolicitarEstagio> solicitacoes = solicitacaoRepository.findAll();
    if (solicitacoes.isEmpty()) {
        return ResponseEntity.notFound().build(); // Se não encontrar, retorna 404 Not Found
    } else {
        return ResponseEntity.ok(solicitacoes); // Se encontrar, retorna 200 OK com a lista de entidades
    }
}


    @GetMapping("/listarSolicitacoesPorEmailServidor")
    public ResponseEntity<List<DadosListagemSolicitacaoServidor>> listarSolicitacoesPorEmailServidor(@RequestHeader("Authorization") String token) {
        String email = tokenService.getSubject(token.replace("Bearer ", ""));
        var servidor = servidorRepository.findByUsuarioSistemaEmail(email);
        List<SolicitarEstagio> solicitacoes;
        if(servidor.getCargo().equals("Coordenador")){
           solicitacoes = solicitacaoRepository.findByServidorAndEtapaIsGreaterThanEqual(servidor, "2");
        }else {
            solicitacoes = solicitacaoRepository.findAll();
        }

        List<DadosListagemSolicitacaoServidor> dadosSolicitacoes = solicitacoes.stream().map(DadosListagemSolicitacaoServidor::new).toList();
        
        return ResponseEntity.ok(dadosSolicitacoes);
    }

    @GetMapping("/alunoSolicitacao/{id}")
    public ResponseEntity<Aluno> getAlunoSolicitacao(@PathVariable("id") Long id) {
        Optional<SolicitarEstagio> solicitacao = solicitacaoRepository.findById(id);
        if (solicitacao.isPresent()) {
            return ResponseEntity.ok(solicitacao.get().getAluno());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/documentosSolicitacao/{id}")
    public ResponseEntity<List<Documento>> getDocumentosSolicitacao(@PathVariable("id") Long id) {
        Optional<SolicitarEstagio> solicitacao = solicitacaoRepository.findById(id);
        if (solicitacao.isPresent()) {
            return ResponseEntity.ok(solicitacao.get().getDocumento());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deferirSolicitacao/{id}")
    @Transactional
    public ResponseEntity deferirSolicitacao(@PathVariable("id") Long id,
                                                       @RequestPart("dados") DadosAtualizacaoSolicitacao dados,
                                                       @RequestParam("file") List<MultipartFile> files,
                                                       @RequestHeader("Authorization") String token) {

        String email = tokenService.getSubject(token.replace("Bearer ", ""));
        Servidor servidor = servidorRepository.findByUsuarioSistemaEmail(email);
        Optional<SolicitarEstagio> solicitacaoOptional = solicitacaoRepository.findById(id);
        System.out.println("AQUI ESTA A ROLE "+servidor.getRole());

         if (servidor.getRole().getId() == 3) {
            System.out.println("Está chegando os dados para a role 3 setor estagio");

            if (solicitacaoOptional.isPresent()) {
                SolicitarEstagio solicitacao = solicitacaoOptional.get();
                
                solicitacao.setEtapa("3");
                solicitacaoRepository.save(solicitacao);
                return ResponseEntity.ok().build();
            }
        }
        
        else if (servidor.getRole().getId() == 2) {
            System.out.println("Está chegando os dados para a role 2 servidor");
            if (solicitacaoOptional.isPresent()) {
                SolicitarEstagio solicitacao = solicitacaoOptional.get();
                //ARRUMAR SEPARACAO LISTA DOCUMENTOS ASSINADOS.
                fileImp.SaveDocBlob(files,solicitacao);
                solicitacao.setEtapa("4");
                solicitacaoRepository.save(solicitacao);
                return ResponseEntity.ok().build();
            }
        }
        
        else if (servidor.getRole().getId() == 4) {
            System.out.println("Está chegando os dados para a role 4 diretor");

            if (solicitacaoOptional.isPresent()) {
                SolicitarEstagio solicitacao = solicitacaoOptional.get();
                //ARRUMAR SEPARACAO LISTA DOCUMENTOS ASSINADOS.
                fileImp.SaveDocBlob(files,solicitacao);
                solicitacao.setEtapa("5");
                solicitacaoRepository.save(solicitacao);
                return ResponseEntity.ok().build();
            }

        }else {
            System.out.println("Está chegando os dados para outras roles");
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


    // @PutMapping("/deferirSolicitacaoSetorEstagio/{id}")
    // @Transactional
    // public ResponseEntity deferirSolicitacaoSetorEstagio(@PathVariable("id") Long id,
    //                                          @RequestPart("dados") DadosAtualizacaoSolicitacao dados,
    //                                          @RequestHeader("Authorization") String token){

    //     String email = tokenService.getSubject(token.replace("Bearer ", ""));
    //     Servidor servidor = servidorRepository.findByUsuarioSistemaEmail(email);
    //     Optional<SolicitarEstagio> solicitacaoOptional = solicitacaoRepository.findById(id);

    //     if(servidor.getRole().getId().equals(3)){
    //         System.out.println("esta chagando os dados");
    //     }
    //     if (solicitacaoOptional.isPresent()) {
    //         SolicitarEstagio solicitacao = solicitacaoOptional.get();
    //         if (dados.status() != null) {
    //             solicitacao.setStatus(dados.status());
    //         }
    //         solicitacao.setEtapa("3");
    //         solicitacaoRepository.save(solicitacao);
    //         historicoSolicitacao.mudarSolicitacao(solicitacao);
    //         return ResponseEntity.ok().build();
    //     }
    //     return ResponseEntity.notFound().build();
    // }


    @PostMapping("/indeferirSolicitacao")
    @Transactional
    public ResponseEntity indeferirSolicitacao1(           @RequestBody DadosAtualizacaoSolicitacao Solicitar,
                                                          @RequestHeader("Authorization") String token){
        String email = tokenService.getSubject(token.replace("Bearer ", ""));
        Servidor servidor = servidorRepository.findByUsuarioSistemaEmail(email);
        System.out.println("ID:" + Solicitar.id());
        System.out.println("DADOS: " + Solicitar.status());
        Optional<SolicitarEstagio> solicitacaoOptional = solicitacaoRepository.findById( Solicitar.id());

        if(servidor.getRole().getId().equals(2)){

        }
        if (solicitacaoOptional.isPresent()) {
            SolicitarEstagio solicitacao = solicitacaoOptional.get();


            solicitacao.setEtapa("6");


            if (Solicitar.status() != null) {
                solicitacao.setStatus(Solicitar.status());
            }


            if (Solicitar.resposta() != null) {
                solicitacao.setResposta(Solicitar.resposta());
            }

            solicitacaoRepository.save(solicitacao);
            historicoSolicitacao.mudarSolicitacao(solicitacao);
            return ResponseEntity.ok().build();
        }
            return ResponseEntity.notFound().build();

    }
    
}