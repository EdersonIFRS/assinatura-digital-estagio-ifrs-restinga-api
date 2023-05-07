package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.example.CadastroServidor.CadastroServidorApplication;
import com.example.CadastroServidor.domain.Interfaces.ServidorInterf;
import com.example.CadastroServidor.domain.classes.Servidor;



public class InserirServidor {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(CadastroServidorApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		ServidorInterf servidorRepository = applicationContext.getBean(ServidorInterf.class);
		
		
		Servidor novoServidor = new Servidor();
		
		novoServidor.setId(5l);
		
		servidorRepository.salvar(novoServidor);
		
		

	}

}
