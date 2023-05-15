package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto;


    public class DocumentoDto {
        private Long id;
        private String nome;

        // Construtores, getters e setters

        public DocumentoDto() {
        }

        public DocumentoDto(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

