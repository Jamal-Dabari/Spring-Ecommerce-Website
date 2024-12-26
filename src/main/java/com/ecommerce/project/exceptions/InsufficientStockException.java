package com.ecommerce.project.exceptions;

public class InsufficientStockException extends RuntimeException {

	public InsufficientStockException() {
		super("insufficient stock available");
	}

}
