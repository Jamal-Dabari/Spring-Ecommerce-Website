package com.ecommerce.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.CartItem;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.repositories.CartRepository;
import com.ecommerce.project.repositories.ProductRepository;

@Service
public class CartServiceImp implements CartService {

  private Long newId = 1L;

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Double getTotalPrice(Long cartId) {
    Cart cart = cartRepository.findByCartId(cartId);
    return cart.getItems().stream().mapToDouble(CartItem::getPrice).sum();
  }

  public Double setTotalPrice(long cartId) {
    double price = 0.0;
    return price;
  }

  @Override
  public void addProducts(long cartId, long productId, int quantity) {
    Cart cart = cartRepository.findByCartId(cartId);
    Product product = productRepository.findById(productId).orElseThrow();

    CartItem item = new CartItem();
    item.setProduct(product);
    item.setQuantity(quantity);
    item.setPrice(product.getProductPrice() * quantity);

    cart.getItems().add(item);
    cart.setTotalPrice(getTotalPrice(cartId) + item.getPrice());

    cartRepository.save(cart);
  }

  @Override
  public void removeProducts(Product product, Long cartId) {
    Cart cart = cartRepository.findByCartId(cartId);

    if (cart == null) {
      throw new IllegalArgumentException("Cart not found for ID " + cartId);
    }

    cart.getItems().removeIf(item -> item.getProduct().equals(product));
    cart.setTotalPrice(getTotalPrice(cartId)); // Recalculate the total price after removal
    cartRepository.save(cart);
  }

  @Override
  public Cart getCartById(Long cartId) {
    Cart cart = cartRepository.findByCartId(cartId);
    if (cart == null) {
      throw new IllegalArgumentException("cart not found");
    }
    return cart;
  }

  @Override
  public Cart createCart(Cart cart) {
    cart.setCartId(newId++);
    return cartRepository.save(cart);
  }
}
