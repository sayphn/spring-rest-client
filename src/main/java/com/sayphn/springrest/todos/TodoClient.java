package com.sayphn.springrest.todos;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@EnableConfigurationProperties(TodoClientConfiguration.class)
public class TodoClient {
    private final RestClient restClient;
    private final TodoClientConfiguration configuration;

    public TodoClient(RestClient.Builder builder,
                      TodoClientConfiguration configuration) {
        this.configuration = configuration;
        this.restClient = builder
                .baseUrl(configuration.url())
                .requestFactory(new JdkClientHttpRequestFactory())
                .build();
    }

    public TodoResponse fetchById(int id) {
        return restClient.get()
                .uri("/todos/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(TodoResponse.class);
    }

    public List<TodoResponse> fetchAll() {
        return restClient.get()
                .uri("/todos")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

}
