package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Usuario;

public record DadosDetalhamentoAluno(Long id, String nome, Usuario usuarioSistema, String turno, String matricula, String ingresso, String curso) {
    public DadosDetalhamentoAluno(Aluno aluno){
        this(aluno.getId(),aluno.getNomeCompleto(),aluno.getUsuarioSistema(), aluno.getTurno(), aluno.getMatricula(), aluno.getIngresso(), aluno.getCurso());
    }
}