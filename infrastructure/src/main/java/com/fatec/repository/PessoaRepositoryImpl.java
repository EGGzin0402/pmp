package com.fatec.repository;

import com.fatec.entity.Pessoa;
import com.fatec.exception.EntityNotFoundException;
import com.fatec.persistence.PessoaPersistence;
import com.fatec.repository.mapper.PessoaRepositoryMapper;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository{

    private final PessoaJpaRepository jpaRepository;
    private final Logger logger = LoggerFactory.getLogger(PessoaRepositoryImpl.class);

    public PessoaRepositoryImpl(PessoaJpaRepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        logger.info("Pessoa Service - Salvando pessoa: {}", pessoa);
        PessoaPersistence pessoaPersistence = PessoaRepositoryMapper.toPersistence(pessoa);
        pessoaPersistence.setAtivo(true);
        Pessoa p = PessoaRepositoryMapper.toEntity(jpaRepository.save(pessoaPersistence));
        logger.info("Pessoa Service - Pessoa salva: {}", p);
        return p;
    }

    @Override
    public Pessoa findById(Long id) {
        logger.info("Pessoa Service - Encontrando pessoa com id: {}", id);
        Optional<PessoaPersistence> pessoa = jpaRepository.findById(id);
        Optional<Pessoa> pessoaEntity = pessoa.map(PessoaRepositoryMapper::toEntity);
        if (pessoaEntity.isPresent()) {
            Pessoa p = pessoaEntity.get();
            logger.info("Pessoa Service - Pessoa encontrada: {}", p);
            return p;
        }
        else {
            throw new EntityNotFoundException("Pessoa não encontrada ou inativa com o id: " + id);
        }
    }

    @Override
    public List<Pessoa> listAll() {
        logger.info("Pessoa Service - Listando todas as pessoas");
        List<PessoaPersistence> pessoasPersistence = jpaRepository.findAll();
        return pessoasPersistence.stream()
                .map(PessoaRepositoryMapper::toEntity)
                .toList();
    }

    @Override
    public Pessoa update(Long id, Pessoa pessoa) {
        logger.info("Pessoa Service - Atualizando pessoa com id: {}", id);
        if(!jpaRepository.existsById(id)){
            throw new EntityNotFoundException("Pessoa não encontrada ou inativa com o id: " + id);
        }
        PessoaPersistence pessoaPersistence = PessoaRepositoryMapper.toPersistence(pessoa);
        pessoaPersistence.setId(id);
        Pessoa p = PessoaRepositoryMapper.toEntity(jpaRepository.save(pessoaPersistence));
        logger.info("Pessoa Service - Pessoa atualizada: {}", p);
        return p;
    }

    @Override
    public void delete(Long id) {
        logger.info("Pessoa Service - Deletando pessoa com id: {}", id);
        if(!jpaRepository.existsById(id)){
            throw new EntityNotFoundException("Pessoa não encontrada ou inativa com o id: " + id);
        }
        jpaRepository.deleteById(id);
    }
}
