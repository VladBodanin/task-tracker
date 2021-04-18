package com.test.tracker.exception.handler;

public class ErrorResponse {
  private final int code;
  private final String message;

  ErrorResponse(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
