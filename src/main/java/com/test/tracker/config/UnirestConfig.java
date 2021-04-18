package com.test.tracker.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class UnirestConfig {

  private final ObjectMapper mapper;

  @PostConstruct
  public void initUnirest() {
    Unirest.config()
        .setObjectMapper(
            (new kong.unirest.ObjectMapper() {

              public String writeValue(Object value) {
                try {
                  return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                  throw new RuntimeException(e);
                }
              }

              public <T> T readValue(String value, Class<T> valueType) {
                try {
                  return mapper.readValue(value, valueType);
                } catch (Exception e) {
                  throw new RuntimeException(e);
                }
              }
            }));
  }
}
