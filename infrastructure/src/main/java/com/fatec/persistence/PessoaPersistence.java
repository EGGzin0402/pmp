package com.fatec.persistence;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pessoa")
@SQLDelete(sql = "UPDATE pessoa SET ativo = false WHERE id = ?")
@SQLRestriction("ativo <> false")
public class PessoaPersistence implements Serializable{

    public PessoaPersistence() {
    }

    public PessoaPersistence(Long id, String nome, LocalDateTime dtNasc, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.ativo = ativo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dt_nasc")
    private LocalDateTime dtNasc;

    @Column(name = "dt_criacao")
    private LocalDateTime dtCriacao;
    @Column(name = "dt_modificacao")
    private LocalDateTime dtModificacao;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    // Getters and Setters
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

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public LocalDateTime getDtModificacao() {
        return dtModificacao;
    }

    public void setDtModificacao(LocalDateTime dtModificacao) {
        this.dtModificacao = dtModificacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PessoaPersistence pessoaPersistence = (PessoaPersistence) o;
        return Objects.equals(id, pessoaPersistence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // toString method
    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                '}';
    }
}
