package com.ecommerce.project.service;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.Product;

public interface CartService {

  Double getTotalPrice(Long cartId);

  void addProducts(long cartId, long productId, int quantity);

  void removeProducts(Product product, Long cartId);

  Cart getCartById(Long cartId);

  Cart createCart(Cart cart);

}
