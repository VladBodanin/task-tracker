package com.test.tracker.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CommentDto {
  private BigInteger id;
  private UserDto author;
  private String value;
}
