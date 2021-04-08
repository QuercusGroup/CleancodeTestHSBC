package com.test.exception;

/*
  Simple Exception class to handle errors.
 */
public class BookException extends Exception{
  public BookException(String message) {
    super(message);
  }
}
