package com.ecommerce.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.CartItem;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.service.CartService;
import com.ecommerce.project.DTO.AddProductRequestDTO;
import com.ecommerce.project.exceptions.UsernameNotFoundException;

@ControllerAdvice
@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  private CartService cartService;

  @GetMapping("public/price/{cart_id}")
  public Double getTotalPrice(@PathVariable long cart_id) {
    return cartService.getTotalPrice(cart_id);
  }

  @GetMapping("public/api/v1/cart/{cart_id}")
  public ResponseEntity<Cart> getCart(@PathVariable Long cart_id) {
    Cart cart = cartService.getCartById(cart_id);
    return ResponseEntity.ok(cart);
  }

  @PostMapping("/api/{cart_id}")
  public ResponseEntity<?> addProduct(@PathVariable long cart_id, @RequestBody AddProductRequestDTO request) {
    try {
      request.setCart_id(cart_id);
      Cart updatedCart = cartService.addProducts(request);
      return ResponseEntity.ok(updatedCart);

    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request: " + e.getMessage());

    } catch (UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("An unexpected error occurred: " + e.getMessage());
    }
  }

  @DeleteMapping("/public/{cart_id}")
  public ResponseEntity<Cart> removeProducts(@RequestParam long productId, @PathVariable long cartId) {
    Cart cart = cartService.getCartById(cartId);
    Product product = cart.getItems().stream()
        .filter(item -> item.getProduct().getProductId().equals(productId))
        .findFirst()
        .map(item -> item.getProduct())
        .orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));

    cartService.removeProducts(product, cartId);
    Cart updatedCart = cartService.getCartById(cartId);
    return ResponseEntity.ok(updatedCart);
  }

  @PostMapping("/public/api/v1/cart/create-cart")
  public ResponseEntity<Cart> createCart() {
    Cart newCart = new Cart();
    Cart createdCart = cartService.createCart(newCart);
    return ResponseEntity.ok(createdCart);
  }

  @GetMapping("/view/{username}")
  public ResponseEntity<List<CartItem>> viewCart(@PathVariable String username) {
    try {
      List<CartItem> items = cartService.viewCart(username);
      return ResponseEntity.ok(items);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }
}
