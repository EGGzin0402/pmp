package com.fatec.web.dto;

import jakarta.validation.constraints.NotEmpty;

public class PessoaCreateDto {

    @NotEmpty
    public String nome;
    @NotEmpty
    public String dtNasc;

    // Construtores
    public PessoaCreateDto() {
    }

    public PessoaCreateDto(String nome, String dtNasc) {
        this.nome = nome;
        this.dtNasc = dtNasc;
    }

    // Getters e Setters
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

    // toString
    @Override
    public String toString() {
        return "PessoaCreateDto{" +
                "nome='" + nome + '\'' +
                ", dtNasc='" + dtNasc + '\'' +
                '}';
    }
}
