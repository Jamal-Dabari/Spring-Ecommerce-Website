package com.ecommerce.project.service;

import java.util.List;

import com.ecommerce.project.DTO.AddProductRequestDTO;
import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.CartItem;
import com.ecommerce.project.model.Product;

public interface CartService {

  Double getTotalPrice(Long cartId);

  Cart addProducts(AddProductRequestDTO prd);

  void removeProducts(Product product, Long cartId);

  Cart getCartById(Long cartId);

  Cart createCart(Cart cart);

  List<CartItem> viewCart(String username);

}
