package com.ecommerce.project.service;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.Order;

public interface OrderService {

  Order placeOrder(long userId, long cartId);

  Order modifyOrder(long order_id, long product_id, int quantity);

  double updateProductQuantity(long productid);

  Cart clearCart(long userId);

}
