package com.fatec.pmp_lambda.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ForgetFunction {

    Logger log = LoggerFactory.getLogger(ForgetFunction.class);

    @Bean
    public Consumer<String> forget() {
        return message ->  log.info("Enviar um email de recuperação para o usuário: {}", message);
    }

}
