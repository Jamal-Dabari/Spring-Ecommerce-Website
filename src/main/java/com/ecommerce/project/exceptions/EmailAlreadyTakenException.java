package com.ecommerce.project.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {

  public EmailAlreadyTakenException() {
    super("the email provided has already been used");
  }

}
