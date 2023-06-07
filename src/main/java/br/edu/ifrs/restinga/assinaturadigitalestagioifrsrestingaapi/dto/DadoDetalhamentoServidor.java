package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Servidor;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;

public record DadoDetalhamentoServidor(Long id, String nome,String curso,String cargo, Usuario usuarioSistema) {
    public DadoDetalhamentoServidor(Servidor servidor) {
        this(servidor.getId(),servidor.getNome(),servidor.getCurso().getNomeCurso(), servidor.getCargo(), servidor.getUsuarioSistema());
    }
}
