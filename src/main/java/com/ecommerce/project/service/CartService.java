package com.ecommerce.project.service;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.Product;

public interface CartService {

  Double getTotalPrice(Long cartId);

  void addProducts(Product product, Long cartId);

  void removeProducts(Product product, Long cartId);

  Cart getCartById(Long cartId);

  Cart createCart(Cart cart);

}
