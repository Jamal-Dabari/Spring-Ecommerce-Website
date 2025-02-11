package com.ecommerce.project.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cartId;

  @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<CartItem> items = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private double totalprice = 0;

  public Cart(Long cartId, List<CartItem> items, User user) {
    this.cartId = cartId;
    this.items = items != null ? items : new ArrayList<>();
    this.user = user;
  }

  public Cart() {

  }

  public Long getCartId() {
    return cartId;
  }

  public void setCartId(Long cartId) {
    this.cartId = cartId;
  }

  public List<CartItem> getItems() {
    return items;
  }

  public void setItems(List<CartItem> items) {
    this.items = items;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public double getTotalPrice() {
    return totalprice;
  }

  public void setTotalPrice(double totalprice) {
    this.totalprice = totalprice;
  }

  // In the Cart class
  public void addCartItem(CartItem newItem) {
    for (CartItem item : items) {
      if (item.getProduct().getProductId().equals(newItem.getProduct().getProductId())) {
        item.setQuantity(item.getQuantity() + newItem.getQuantity());
        item.setPrice(item.getProduct().getProductPrice() * item.getQuantity());
        recalculateTotalPrice();
        return;
      }
    }
    items.add(newItem);
    recalculateTotalPrice();
  }

  private void recalculateTotalPrice() {
    totalprice = items.stream()
        .mapToDouble(item -> item.getPrice())
        .sum();
  }

  public void clearCart() {
    items.clear();
  }

}
