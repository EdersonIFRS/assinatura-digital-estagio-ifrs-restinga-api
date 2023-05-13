package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;

public record DadosCadastroAluno(

    String nomeCompleto,
    Usuario usuarioSistema,
    String turno,
    String matricula,
    String ingresso,
    String curso

) {
    
}
