package com.fatec.entity;

import java.time.LocalDateTime;

public class Pessoa{
    Long id;
    String nome;
    LocalDateTime dtNasc;
    boolean ativo;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, LocalDateTime dtNasc, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.ativo = ativo;
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

    public LocalDateTime getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDateTime dtNasc) {
        this.dtNasc = dtNasc;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
