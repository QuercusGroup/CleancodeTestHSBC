package com.test.exception;

/*
  Simple Exception class to handle errors.
 */
public class LibraryException extends Exception{
  public LibraryException(String message) {
    super(message);
  }
}
