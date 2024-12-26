package com.ecommerce.project.exceptions;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException() {
		super("Product Not Found");
	}

}
