package com.ecommerce.project.exceptions;

public class EmailNotFoundException extends RuntimeException {

  public EmailNotFoundException() {
    super("The Email Does Not exist");
  }

}
