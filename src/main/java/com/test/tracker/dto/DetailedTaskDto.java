package com.test.tracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DetailedTaskDto {
  private BigInteger id;
  private Instant createdAt;
  private String topic;
  private String description;
  private UserDto author;
  private UserDto performer;
  private TaskState taskState;
  private UnitDto unitEntity;
  private List<CommentDto> comments;
  private List<AttachmentDto> attachments;
}
