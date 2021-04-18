package com.test.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

@AllArgsConstructor
@Data
public class UserRateDto {
  private Map<BigInteger, BigDecimal> rates;
}
