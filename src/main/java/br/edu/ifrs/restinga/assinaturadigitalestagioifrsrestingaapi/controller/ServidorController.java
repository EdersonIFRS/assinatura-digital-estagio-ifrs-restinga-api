package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.ImplClasses.ServidorImplementacao;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DadosCadastroServidor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@ResponseBody
public class ServidorController {
	
	//CRUD PARA CLASSE SERVIDOR
    @Autowired
    private ServidorImplementacao servidorImplementacao;


    @PostMapping("/cadastroServidor")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity salvar(@RequestBody @Valid DadosCadastroServidor dadosCadastroServidor, UriComponentsBuilder uriBuilder) {
        return servidorImplementacao.salvar(dadosCadastroServidor,uriBuilder);
    }


    @GetMapping("/buscarServidor/{id}")
    public ResponseEntity buscarServidrorId(@PathVariable Long id) {
        var servidor = servidorImplementacao.findId(id);
        return ResponseEntity.ok(servidor);

    }


    @GetMapping("/listarServidores")
    public ResponseEntity listarServidores(){
        var servidor = servidorImplementacao.listar();
        return ResponseEntity.ok(servidor);
    }
	
//	//Método para teste se retorna dados do servidor de id 1
//	/*
//	@RequestMapping("/alo")
//	@ResponseBody
//	public String serv1() {
//
//		return servInt.buscar(1l).getNome();
//
//	}*/
//
//
//	//Método POST para receber dados para cadastro do servidor
//
//	@PostMapping("/cadastroServidor")
//	@ResponseStatus(HttpStatus.CREATED)
//	public ResponseEntity salvar(@RequestBody @Valid Servidor servidor) {
//
//		servInt.salvar(servidor);
//		return ResponseEntity.noContent().build();
//	}
//
//
//	//GET
//
//	//Buscar Servidor por id
//	@GetMapping("/buscar/{id}")
//	public ResponseEntity buscar(@PathVariable Long id) {
//
//		Servidor serv = servInt.buscar(id);
//		return ResponseEntity.ok(serv);
//
//	}
//
//	/*
//	@GetMapping("/buscar/{id}")
//	public Servidor buscar(@PathVariable Long id) {
//
//		Servidor serv = servInt.buscar(id);
//		return serv;
//
//	}
//	*/
//
//
//
//	@GetMapping("/listarServidores")
//	public ResponseEntity listar(){
//
//		return ResponseEntity.ok(servInt.listar());
//	}
//
//
//
//	//Atualizar Servidor
//
//	@PutMapping("/atualizar/{id}")
//	public ResponseEntity atualizar(@RequestBody Servidor servidor, @PathVariable Long id) {
//	    Servidor servidorExistente = servInt.buscar(id);
//	    BeanUtils.copyProperties(servidor, servidorExistente, "id");
//	    servInt.salvar(servidorExistente);
//
//		return ResponseEntity.noContent().build();
//	}
//
//
//	//  DELETE para remover servidor por id
//	@DeleteMapping("/del/{id}")
//	public ResponseEntity remover(@PathVariable Long id) {
//	    Servidor servidorExistente = servInt.buscar(id);
//	    servInt.remover(servidorExistente);
//
//		return ResponseEntity.noContent().build();
//	}
//
		  


}
