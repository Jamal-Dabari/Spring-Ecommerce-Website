package com.ecommerce.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.repositories.CartRepository;

@Service
public class CartServiceImp implements CartService {

  @Autowired
  private CartRepository cartRepository;

  @Override
  public Double getTotalPrice(Long cartId) {
    Cart cart = cartRepository.findByCartId(cartId);
    return cart.getProducts().stream().mapToDouble(Product::getProductPrice).sum();
  }

  @Override
  public void addProducts(Product product, Long cartId) {
    Cart cart = cartRepository.findByCartId(cartId);
    cart.getProducts().add(product);
    cart.setQuantity(cart.getQuantity() + 1);
    cartRepository.save(cart);
  }

  @Override
  public void removeProducts(Product product, Long cartId) {
    Cart cart = cartRepository.findByCartId(cartId);
    cart.getProducts().remove(product);
    cart.setQuantity(cart.getQuantity() - 1);
    cartRepository.delete(cart);
  }

  @Override
  public Cart getCartById(Long cartId) {
    return cartRepository.findByCartId(cartId);
  }

  @Override
  public Cart createCart(Cart cart) {
    return cartRepository.save(cart);
  }
}
