package com.fatec.repository;

import com.fatec.entity.Pessoa;

import java.util.List;

public interface PessoaRepository {

    Pessoa save(Pessoa pessoa);
    Pessoa findById(Long id);
    List<Pessoa> listAll();
    Pessoa update(Long id, Pessoa pessoa);
    void delete(Long id);

}
