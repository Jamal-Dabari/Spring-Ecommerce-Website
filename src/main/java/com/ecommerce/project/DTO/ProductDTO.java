package com.ecommerce.project.DTO;

public record ProductDTO(
    Long productId,
    String productName,
    Double productPrice,
    Long productQuantity) {

}
