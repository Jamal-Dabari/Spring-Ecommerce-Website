package com.ecommerce.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  private CartService cartService;

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

}
