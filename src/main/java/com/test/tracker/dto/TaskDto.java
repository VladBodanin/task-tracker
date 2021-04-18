package com.test.tracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigInteger;
import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TaskDto {
  private BigInteger id;
  private Instant createdAt;
  private String topic;
  private UserDto author;
  private UserDto performer;
  private TaskState taskState;
  private UnitDto unitEntity;
}
