package com.test.tracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {
  private BigInteger id;
  private String name;
  private UnitDto unitDto;
  private BigDecimal rate;
}
