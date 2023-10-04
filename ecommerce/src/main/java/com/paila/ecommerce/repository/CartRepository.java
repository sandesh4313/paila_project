package com.paila.ecommerce.repository;

import com.paila.ecommerce.entity.Cart;
import com.paila.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findByUser(User user);
}
