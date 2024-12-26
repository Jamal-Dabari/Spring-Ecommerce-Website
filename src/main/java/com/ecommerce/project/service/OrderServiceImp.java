
package com.ecommerce.project.service;

import javax.naming.InsufficientResourcesException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project.exceptions.InsufficientStockException;
import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.CartItem;
import com.ecommerce.project.model.Order;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.repositories.CartRepository;
import com.ecommerce.project.repositories.OrderRepository;
import com.ecommerce.project.repositories.ProductRepository;

@Service
public class OrderServiceImp implements OrderService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Order placeOrder(long userId, long cartId) {
    Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));

    for (CartItem item : cart.getItems()) {
      if (item.getQuantity() > item.getProduct().getProductQuantity()) {
        throw new InsufficientStockException();
      }
    }

    Order order = new Order();
    order.setUserId(userId);
    order.setCart(cart);
    order.setTotal(cart.getTotalPrice());
    orderRepository.save(order);

    for (CartItem item : cart.getItems()) {
      Product product = item.getProduct();
      product.setProductQuantity(product.getProductQuantity() - item.getQuantity());
      productRepository.save(product);
    }

    cart.clearCart();
    cartRepository.save(cart);

    return order;

  }

  @Override
  public Order modifyOrder(long order_id, long product_id, int quantity) {

  }

  @Override
  public double updateProductQuantity(long productid) {

  }

  @Override
  public Cart clearCart(long userId) {

  }

}
