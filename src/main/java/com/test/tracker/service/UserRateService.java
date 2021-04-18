package com.test.tracker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserRateService {

  private final ObjectMapper mapper;

  @Value("${rate-service-host}")
  private String rateServiceHost;

  public Map<BigInteger, BigDecimal> getUserRates(List<BigInteger> ids) {
    try {
      String responseString =
          Unirest.get(rateServiceHost).queryString("ids", ids).asString().getBody();

      return mapper.readValue(responseString, new TypeReference<>() {});
    } catch (IOException e) {
      log.error(e.getMessage());
      return Collections.emptyMap();
    }
  }
}
