package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssinaturaDigitalEstagioIfrsRestingaApiApplication {

	HistoricoSolicitacao historico;
	public static void main(String[] args) {
		SpringApplication.run(AssinaturaDigitalEstagioIfrsRestingaApiApplication.class, args);
	}

}
