package com.paila.ecommerce.repository;


import com.paila.ecommerce.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepo extends JpaRepository<OrderItems, Integer> {
}
