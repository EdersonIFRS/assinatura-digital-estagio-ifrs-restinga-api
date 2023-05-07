package com.example.CadastroServidor.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.CadastroServidor.CRUD.InserirServidor;
import com.example.CadastroServidor.domain.Interfaces.ServidorInterf;
import com.example.CadastroServidor.domain.classes.Servidor;
import com.example.CadastroServidor.infra.ImplClasses.ServidorImpl;

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

	   @PostMapping("/post")
	   @ResponseStatus(HttpStatus.CREATED)
	    public Servidor salvar(@RequestBody Servidor servidor) {
	        return servInt.salvar(servidor);
	    }
	   
	   
	//GET	   

	   //Buscar Servidor por id
	   @GetMapping("/buscar/{id}")
	    public Servidor buscar(@PathVariable Long id) {
	        return servInt.buscar(id);
	    }
		  
	   
	   
	   //Atualizar Servidor
	   
	
		@PutMapping("/atualizar/{id}")
		public Servidor atualizar(@RequestBody Servidor servidor, @PathVariable Long id) {
		    Servidor servidorExistente = servInt.buscar(id);
		    BeanUtils.copyProperties(servidor, servidorExistente, "id");
		    return servInt.salvar(servidorExistente);
		}
		
		
		//  DELETE para remover servidor
		@DeleteMapping("/del/{id}")
		public void remover(@PathVariable Long id) {
		    Servidor servidorExistente = servInt.buscar(id);
		    servInt.remover(servidorExistente);
		}
		   
		  


}
