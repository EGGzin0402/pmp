package com.fatec.repository;

import com.fatec.persistence.PessoaPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJpaRepository extends JpaRepository<PessoaPersistence, Long> {
}