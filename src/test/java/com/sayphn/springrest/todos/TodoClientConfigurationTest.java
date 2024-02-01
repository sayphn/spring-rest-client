package com.sayphn.springrest.todos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoClientConfigurationTest {
    @Autowired
    private TodoClientConfiguration configuration;

    @Test
    void should_bind_url_from_properties_file() {
        assertThat(configuration.url()).isEqualTo("https://jsonplaceholder.typicode.com");
    }
}