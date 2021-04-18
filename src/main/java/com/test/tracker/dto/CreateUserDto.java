package com.test.tracker.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class CreateUserDto {
  @NotBlank(message = "name should be NotBlank")
  private String name;

  @NotNull(message = "unitId should be NotNull")
  private BigInteger unitId;
}
