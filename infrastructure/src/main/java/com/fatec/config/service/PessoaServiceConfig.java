package com.fatec.config.service;

import com.fatec.repository.PessoaRepository;
import com.fatec.service.PessoaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaServiceConfig {
    @Bean
    public PessoaService pessoaService(PessoaRepository repository){
        return new PessoaService(repository);
    }
}
