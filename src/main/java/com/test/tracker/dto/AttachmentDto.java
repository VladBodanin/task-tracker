package com.test.tracker.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AttachmentDto {
  private BigInteger id;
  private String downloadLink;
  private BigInteger taskId;
}
