package com.ecommerce.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project.DTO.AddProductRequestDTO;
import com.ecommerce.project.exceptions.CartNotFoundException;
import com.ecommerce.project.exceptions.ProductNotFoundException;
import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.CartItem;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repositories.CartRepository;
import com.ecommerce.project.repositories.ProductRepository;
import com.ecommerce.project.repositories.UserRepository;

@Service
public class CartServiceImp implements CartService {

  private Long newId = 1L;

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private UserRepository userRepository;

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
  public Cart addProducts(AddProductRequestDTO prd) {
    Cart cart = cartRepository.findById(prd.getCart_id())
        .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

    Product product = productRepository.findById(prd.getProductId())
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    CartItem cartItem = new CartItem();
    cartItem.setProduct(product);
    cartItem.setQuantity(prd.getQuantity());
    cartItem.setPrice(product.getProductPrice() * prd.getQuantity());
    cartItem.setCart(cart);
    cart.getItems().add(cartItem);

    cartRepository.save(cart);
    return cart;
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

  @Override
  public List<CartItem> viewCart(String username) {
    // Retrieve user by username
    User user = userRepository.findByUsername(username);

    // Find cart by user
    Cart cart = cartRepository.findByUser(user);

    // Check if cart exists for the user
    if (cart == null) {
      throw new IllegalArgumentException("Cart not found for user " + username);
    }

    // Return cart items (initialize to empty list if null)
    return cart.getItems() != null ? cart.getItems() : new ArrayList<>();
  }

}
