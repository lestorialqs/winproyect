package com.dbp.winproyect.exceptions;

public class UnauthorizedOperationException extends RuntimeException {
  public UnauthorizedOperationException(String message) {
    super(message);
  }
}
