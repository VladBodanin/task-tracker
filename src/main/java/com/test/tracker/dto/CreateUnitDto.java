package com.test.tracker.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUnitDto {
  @NotBlank(message = "name should be NotBlank")
  private String name;
}
