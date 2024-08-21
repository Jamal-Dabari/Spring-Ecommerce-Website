package com.ecommerce.project.exceptions;

public class UsernameNotFoundException extends RuntimeException {

  public UsernameNotFoundException() {
    super("Username Not Found");
  }

}
