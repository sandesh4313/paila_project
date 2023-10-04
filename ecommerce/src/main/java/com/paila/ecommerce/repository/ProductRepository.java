package com.paila.ecommerce.repository;

import com.paila.ecommerce.entity.Product;
import com.paila.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory_Id(Integer id);

    List<Product> findByNameContainingIgnoreCase(String keyword);
}
