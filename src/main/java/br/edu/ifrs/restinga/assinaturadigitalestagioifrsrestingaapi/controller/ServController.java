package com.example.CadastroServidor.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Servidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.repository.ServidorInterf;

@Controller
@RestController
@ResponseBody

@CrossOrigin

public class ServController {
	
	//CRUD PARA CLASSE SERVIDOR
	
	
	
	
	@Autowired
	ServidorInterf servInt;
	
	//Método para teste se retorna dados do servidor de id 1
	/*
	@RequestMapping("/alo")
	@ResponseBody	 
	public String serv1() {

		return servInt.buscar(1l).getNome();

	}*/


	//Método POST para receber dados para cadastro do servidor

	@PostMapping("/servidor/cadastroServidor")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity salvar(@RequestBody Servidor servidor) {	
		servInt.salvar(servidor);
		return ResponseEntity.noContent().build();
	}
	   
	   
	//GET	   

	//Buscar Servidor por id
	@GetMapping("/buscar/{id}")
	public ResponseEntity buscar(@PathVariable Long id) {	
		
		servInt.buscar(id);
		return ResponseEntity.ok(servInt.buscar(id));
		
	}
	
	@GetMapping("/listarServidores")	
	public ResponseEntity listar(){
				
		return ResponseEntity.ok(ServInt.listar());
	}
		  
	   
	   
	//Atualizar Servidor

	@PutMapping("/atualizar/{id}")
	public ResponseEntity atualizar(@RequestBody Servidor servidor, @PathVariable Long id) {
	    Servidor servidorExistente = servInt.buscar(id);
	    BeanUtils.copyProperties(servidor, servidorExistente, "id");
	    servInt.salvar(servidorExistente);
		
		return ResponseEntity.noContent().build();
	}

		
	//  DELETE para remover servidor por id
	@DeleteMapping("/del/{id}")
	public ResponseEntity remover(@PathVariable Long id) {
	    Servidor servidorExistente = servInt.buscar(id);
	    servInt.remover(servidorExistente);
		
		return ResponseEntity.noContent().build();
	}
		   
		  


}
