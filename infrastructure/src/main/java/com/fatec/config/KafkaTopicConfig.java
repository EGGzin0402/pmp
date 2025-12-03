package com.fatec.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic forgetTopic(
            @Value("${spring.kafka.topic.forget}") String topicName,
            @Value("${spring.kafka.partitions}") int partitions,
            @Value("${spring.kafka.replicas}") short replicas) {
        return new NewTopic(topicName, partitions, replicas);
    }
}
