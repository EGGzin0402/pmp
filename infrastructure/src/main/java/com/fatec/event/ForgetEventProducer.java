package com.fatec.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class ForgetEventProducer {

    private final String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ForgetEventProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${spring.kafka.topic.forget}") String topic) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String username) throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplate.send(topic, username)
                .get(5000, TimeUnit.MILLISECONDS);
    }

}