package com.test.tracker.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class CreateCommentDto {
  @NotNull(message = "userId should be NotNull")
  private BigInteger userId;

  @NotBlank(message = "value should be NotBlank")
  private String value;
}
