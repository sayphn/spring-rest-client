package com.sayphn.springrest.todos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(TodoClient.class)
class TodoClientTest {
    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TodoClient client;

    @Test
    void should_return_specific_todo_when_get_by_id() throws JsonProcessingException {
        // Arrange
        var mockResponse = new TodoResponse(1, 1, "Titre super", false);

        mockServer.expect(once(), requestTo(matchesPattern(".*/todos/[0-9]+")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withSuccess(
                                objectMapper.writeValueAsString(mockResponse), MediaType.APPLICATION_JSON
                        )
                );

        // Act
        var response = client.fetchById(1);

        // Assert
//        assertThat(response).isEqualTo(mockResponse);
        mockServer.verify();
    }
}