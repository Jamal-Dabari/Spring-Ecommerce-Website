package com.ecommerce.project.exceptions;

public class CartNotFoundException extends RuntimeException {

	public CartNotFoundException() {
		super("Cart Not Found");
	}

}
