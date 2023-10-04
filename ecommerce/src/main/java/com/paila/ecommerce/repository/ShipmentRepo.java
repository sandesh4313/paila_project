package com.paila.ecommerce.repository;


import com.paila.ecommerce.entity.Shipment;
import com.paila.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepo extends JpaRepository<Shipment, Integer> {
    List<Shipment> findByUser(User user);
}
