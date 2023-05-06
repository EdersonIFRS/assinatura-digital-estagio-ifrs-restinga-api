package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model;

public record DadosListagemAluno(
    Long id, 
    String nome, 
    String email, 
    String senha
) {
    public DadosListagemAluno(Aluno aluno){
        this(aluno.getId(),aluno.getNomeCompleto(),aluno.getEmail(),aluno.getSenha());
    }
}
