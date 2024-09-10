package com.ecommerce.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.service.CartService;
import com.ecommerce.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  private CartService cartService;

  @Autowired
  UserService userService;

  @GetMapping("/cart/{cart_id}")
  public Double getTotalPrice(@PathVariable long cartId) {
    return cartService.getTotalPrice(cartId);
  }

  @PostMapping("/cart/{cart_id}")
  public void addProducts(@RequestBody Product product, @PathVariable long cartId) {
    cartService.addProducts(product, cartId);
  }

  @DeleteMapping("/cart/{cart_id}")
  public void removeProducts(@RequestBody Product product, @PathVariable long cartId) {
    cartService.removeProducts(product, cartId);
  }

  @GetMapping("/api/v1/cart/{cart_id}")
  public Cart getCart(@PathVariable Long cartId) {
    return cartService.getCartById(cartId);
  }

  @PostMapping("/api/v1/cart/create-cart")
  public Cart createCart(@RequestBody Cart cart, HttpSession session) {
    String username = (String) session.getAttribute("username");

    if (username != null) {
      User currentUser = (User) userService.loadUserByUsername(username);
      cart.setUser(currentUser);
    }

    return cartService.createCart(cart);
  }

}
