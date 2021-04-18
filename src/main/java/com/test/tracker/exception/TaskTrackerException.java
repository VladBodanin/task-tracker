package com.test.tracker.exception;

import com.test.tracker.exception.handler.ErrorReason;
import lombok.Getter;

@Getter
public class TaskTrackerException extends RuntimeException {

  private final ErrorReason errorReason;

  public TaskTrackerException(ErrorReason errorReason, String message) {
    super(message);
    this.errorReason = errorReason;
  }
}
