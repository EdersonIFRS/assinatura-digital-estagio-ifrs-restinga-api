package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;
    import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.VagaRepository;
    import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosVaga;
    import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Vaga;
    import jakarta.transaction.Transactional;
    import jakarta.validation.Valid;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import java.util.List;

@RestController
    public class VagaController   {
        @Autowired
        VagaRepository vagaRepository;

        @Transactional
        @PostMapping("/cadastrarVaga")
        public ResponseEntity criarVaga(@RequestBody @Valid DadosVaga Dvaga, @RequestHeader("Authorization") String token){
            System.out.println(Dvaga);
            Vaga vaga = new Vaga(Dvaga.titulo(),Dvaga.empresa(),Dvaga.agencia(), Dvaga.descricao(), Dvaga.local(), Dvaga.valor(),Dvaga.turno());
            vagaRepository.save(vaga);
            return ResponseEntity.ok().build();
        }
        @PostMapping("/deletarVaga")
        public ResponseEntity deletarVaga(@RequestParam Long id){
            if(vagaRepository.existsById(id)){
                vagaRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        // @Transactional
        // @PostMapping("/alterarVaga")
        // public ResponseEntity alterarVaga(@RequestBody @Valid DadosVaga Dvaga){
        //     Vaga vaga = new Vaga(Dvaga.id(),Dvaga.titulo(),Dvaga.agencia(), Dvaga.descricao(), Dvaga.local(), Dvaga.valor(),Dvaga.local());
        //     if(vagaRepository.existsById(vaga.getId())){
        //         vagaRepository.save(vaga);
        //         return ResponseEntity.ok().build();
        //     }
        //     else{
        //         return ResponseEntity.notFound().build();
        //     }
        // }
        @GetMapping("/listarVagas")
        public ResponseEntity listarVagas(){
            List<Vaga> vagas = vagaRepository.findAll();
            List<DadosVaga> dadosVagas = vagas.stream()
                    .map(DadosVaga::new)
                    .toList();
            return ResponseEntity.ok(dadosVagas);
        }

    }
