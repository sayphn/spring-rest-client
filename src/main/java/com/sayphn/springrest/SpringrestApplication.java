package com.sayphn.springrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayphn.springrest.todos.TodoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringrestApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringrestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringrestApplication.class, args);
    }

    @Bean
    protected CommandLineRunner commandLineRunner(TodoClient client) {
        return args -> {
            var response = client.fetchById(1);
            logger.info(new ObjectMapper().writeValueAsString(response));
        };
    }
}
