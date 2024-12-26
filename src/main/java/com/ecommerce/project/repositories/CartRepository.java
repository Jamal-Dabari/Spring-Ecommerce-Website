package com.ecommerce.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  Cart findByCartId(Long cartId);

  Cart findByUser(User user);

}
