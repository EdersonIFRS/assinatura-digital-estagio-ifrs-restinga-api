package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Aluno;

public record DadosDetalhamentoAluno(Long id, String nome, String email, String turno, String matricula, String ingresso, String curso, String senha) {
    public DadosDetalhamentoAluno(Aluno aluno){
        this(aluno.getId(),aluno.getNomeCompleto(),aluno.getEmail(), aluno.getTurno(), aluno.getMatricula(), aluno.getIngresso(), aluno.getCurso() ,aluno.getSenha());
    }
}
