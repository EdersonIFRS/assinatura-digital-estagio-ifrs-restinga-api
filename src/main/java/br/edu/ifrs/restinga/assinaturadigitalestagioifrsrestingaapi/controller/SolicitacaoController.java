package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.ImplClasses.FileImp;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosAtualizacaoSolicitacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroSolicitacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosListagemSolicitacaoAluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosListagemSolicitacaoServidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Documento;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Servidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        SolicitarEstagio solicitarEstagio = new SolicitarEstagio(aluno.get(), servidor.get(),dados.tipo(), dados.titulo(), dados.conteudo(), dados.observacao(), "Em andamento", "1", "", "", "Em andamento", "");
        fileImp.SaveDocBlob(file,solicitarEstagio);

        solicitacaoRepository.save(solicitarEstagio);
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
    @GetMapping("/solicitacao/{id}")
    public ResponseEntity<DadosAtualizacaoSolicitacao> getSolicitacaoById(@PathVariable("id") Long id) {
        Optional<SolicitarEstagio> solicitacaoOptional = solicitacaoRepository.findById(id);
        if (solicitacaoOptional.isPresent()) {
            SolicitarEstagio solicitacao = solicitacaoOptional.get();

            DadosAtualizacaoSolicitacao solicitacaoDTO = new DadosAtualizacaoSolicitacao(solicitacao.getStatus(), solicitacao.getEtapa(), solicitacao.getStatusSetorEstagio(),
                    solicitacao.getStatusEtapaCoordenador(), solicitacao.getStatusEtapaDiretor(), solicitacao.getObservacao());

            solicitacaoDTO.statusEtapaDiretor();
            solicitacaoDTO.statusEtapaCoordenador();
            solicitacaoDTO.etapa();
            solicitacaoDTO.statusEtapaSetorEstagio();
            solicitacaoDTO.observacao();
            solicitacaoDTO.status();

            return ResponseEntity.ok(solicitacaoDTO);
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
                                             @RequestParam(value = "file", required = false) List<MultipartFile> files,
                                             @RequestHeader("Authorization") String token) {
        String email = tokenService.getSubject(token.replace("Bearer ", ""));
        Servidor servidor = servidorRepository.findByUsuarioSistemaEmail(email);
        Optional<SolicitarEstagio> solicitacaoOptional = solicitacaoRepository.findById(id);

        if (!solicitacaoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        SolicitarEstagio solicitacao = solicitacaoOptional.get();


        if (solicitacao.getStatus().equals("INDEFERIDO")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Não é possível deferir uma solicitação indeferida.");
        }

        if(solicitacao.getEtapa().equals("5")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Está solicitação já foi concluida como deferida ela não pode mais ser deferida.");
        }

        // se estiver na etapa 1 e não for role 3  role 3 = setor
        if (solicitacao.getEtapa().equals("1") && !( servidor.getRole().getId() == 3))  {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas o setor de estágios pode deferir uma solicitação na etapa 1.");
        }

        // se estiver na etapa 2 e não for role 2 role 2  = coordenador
        if (solicitacao.getEtapa().equals("3") && !(servidor.getRole().getId() == 2)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas o coordenador pode deferir uma solicitação na etapa 2.");
        }

       // se estiver na etapa 3 e não for role 4 role 4  = diretor
        if (solicitacao.getEtapa().equals("4") && !( servidor.getRole().getId() == 4 )) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas o diretor pode deferir uma solicitação na etapa 3.");
        }

        if (servidor.getRole().getId() == 3) {
            solicitacao.setStatusSetorEstagio(dados.statusEtapaSetorEstagio()); // status setor estagio
            solicitacao.setEtapa("2");
            solicitacao.setStatusEtapaCoordenador("Em andamento");
        }

        if (servidor.getRole().getId() == 2) {
            solicitacao.setStatusEtapaCoordenador(dados.statusEtapaCoordenador()); // status coordenador
            solicitacao.setEtapa("4");
            solicitacao.setStatusEtapaDiretor("Em andamento");
        }

        if (servidor.getRole().getId() == 4) {
            solicitacao.setEtapa("5"); // Diretor
            solicitacao.setStatusEtapaDiretor(dados.statusEtapaDiretor()); // definir status
            solicitacao.setStatus(dados.status()); // Definir o status como "DEFERIDO"
        }


        if (files != null && !files.isEmpty()) {
            fileImp.SaveDocBlob(files, solicitacao);
        }

        solicitacaoRepository.save(solicitacao);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/indeferirSolicitacao/{id}")
    @Transactional
    public ResponseEntity indeferirSolicitacao(@PathVariable("id") Long id,
                                               @RequestBody DadosAtualizacaoSolicitacao dados,
                                               @RequestHeader("Authorization") String token) {
        String email = tokenService.getSubject(token.replace("Bearer ", ""));
        Servidor servidor = servidorRepository.findByUsuarioSistemaEmail(email);

        // Verificar se o servidor tem permissão para indeferir solicitações
        if (!(servidor.getRole().getId() == 2 || servidor.getRole().getId() == 3 || servidor.getRole().getId() == 4)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas pessoas do setor de estágios, diretores ou coordenadores podem indeferir uma solicitação.");
        }


        Optional<SolicitarEstagio> solicitacaoOptional = solicitacaoRepository.findById(id);

        if (solicitacaoOptional.isPresent()) {
            SolicitarEstagio solicitacao = solicitacaoOptional.get();

            if(solicitacao.getEtapa().equals("5")){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Está solicitação já foi concluida como deferida ela não pode mais ser indeferida.");
            }

            solicitacao.setEtapa("5");

            solicitacao.setStatus("INDEFERIDO");
            solicitacao.setStatusEtapaDiretor("INDEFERIDO");
            solicitacao.setStatusEtapaCoordenador("INDEFERIDO");
            solicitacao.setStatusEtapaDiretor("INDEFERIDO");

            if (dados.observacao() != null) {
                solicitacao.setObservacao(dados.observacao());
            }

            solicitacaoRepository.save(solicitacao);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
    
}