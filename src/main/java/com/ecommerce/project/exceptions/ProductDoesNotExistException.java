package com.ecommerce.project.exceptions;

public class ProductDoesNotExistException extends RuntimeException {
  public ProductDoesNotExistException() {
    super("Product Does Not Exist");
  }

  public ProductDoesNotExistException(String message) {
    super(message);
  }

}
