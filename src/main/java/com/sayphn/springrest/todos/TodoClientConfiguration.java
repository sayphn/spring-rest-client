package com.sayphn.springrest.todos;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties(prefix = "json-placeholder")
public record TodoClientConfiguration(String url) {
}
