package com.fatec.web.dto;

import com.fatec.web.dto.annotations.NullOrNotBlank;

public class PessoaUpdateDto {

    @NullOrNotBlank(message = "Nome não pode ser vazio")
    public String nome;
    @NullOrNotBlank(message = "Data de Nascimento não pode ser vazia")
    public String dtNasc;

    // Construtores
    public PessoaUpdateDto() {
    }
    public PessoaUpdateDto(String nome, String dtNasc) {
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
        return "PessoaUpdateDto{" +
                "nome='" + nome + '\'' +
                ", dtNasc='" + dtNasc + '\'' +
                '}';
    }
}
