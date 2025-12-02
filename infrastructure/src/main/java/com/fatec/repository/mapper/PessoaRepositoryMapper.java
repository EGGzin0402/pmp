package com.fatec.repository.mapper;

import com.fatec.entity.Pessoa;
import com.fatec.persistence.PessoaPersistence;
import org.modelmapper.ModelMapper;

public class PessoaRepositoryMapper {

    private PessoaRepositoryMapper() {
    }

    public static Pessoa toEntity(PessoaPersistence pessoaPersistence){
        return new ModelMapper().map(pessoaPersistence, Pessoa.class);
    }

    public static PessoaPersistence toPersistence(Pessoa pessoaEntity){
        return new ModelMapper().map(pessoaEntity, PessoaPersistence.class);
    }

}
