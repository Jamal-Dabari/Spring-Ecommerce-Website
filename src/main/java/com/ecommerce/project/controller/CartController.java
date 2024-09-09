package com.ecommerce.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.service.CartService;
import com.ecommerce.project.service.CategoryService;
import com.ecommerce.project.service.ProductService;
import com.ecommerce.project.service.UserService;

@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  private CartService cartService;

  @Autowired
  private UserService UserService;

  @Autowired
  private ProductService productService;

  @Autowired
  private CategoryService categoryService;

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
  public Cart createCart(@RequestBody Cart cart) {
    UserDetails user = UserService.loadUserByUsername(user.getUsername());
    return cart;
  }

}
