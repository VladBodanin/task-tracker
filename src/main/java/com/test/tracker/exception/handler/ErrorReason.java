package com.test.tracker.exception.handler;

public enum ErrorReason {
  INVALID_TASK(1001);

  private final int errorCode;

  ErrorReason(int errorCode) {
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }
}
