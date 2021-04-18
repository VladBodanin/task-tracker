package com.test.tracker.exception;

public class EntityAlreadyExists extends RuntimeException {

  public EntityAlreadyExists(String message) {
    super(message);
  }
}
