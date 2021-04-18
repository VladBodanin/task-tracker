package com.test.tracker.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.Instant;

@Data
public class CreateTaskDto {
  @NotNull(message = "createdAt should be NotNull")
  private Instant createdAt;

  @NotBlank(message = "topic should be NotBlank")
  private String topic;

  private String description;

  @NotNull(message = "authorId should be NotNull")
  private BigInteger authorId;

  @NotNull(message = "performerId should be NotNull")
  private BigInteger performerId;

  @NotNull(message = "unitId should be NotNull")
  private BigInteger unitId;
}
