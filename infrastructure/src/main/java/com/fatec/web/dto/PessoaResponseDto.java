package com.fatec.web.dto;

public class PessoaResponseDto {

    private Long id;
    private String nome;
    private String dtNasc;

    // Construtores
    public PessoaResponseDto() {}

    public PessoaResponseDto(Long id, String nome, String dtNasc) {
        this.id = id;
        this.nome = nome;
        this.dtNasc = dtNasc;
    }

    // Getters e Setters
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

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }



}
