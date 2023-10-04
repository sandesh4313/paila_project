package com.paila.ecommerce.service.impl;


import com.paila.ecommerce.entity.Cart;
import com.paila.ecommerce.entity.Order;
import com.paila.ecommerce.entity.Shipment;
import com.paila.ecommerce.repository.OrderRepo;
import com.paila.ecommerce.repository.ShipmentRepo;
import com.paila.ecommerce.repository.UserRepository;
import com.paila.ecommerce.service.CartService;
import com.paila.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final CartService cartService;

    private final ShipmentRepo shipmentRepo;
    private final UserRepository userRepository;


    @Override
    public Order saveOrderToTable(Shipment shipment) {
        Order newOrder = new Order();

        //Generating a random string for order
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            char randomChar = (char) ((int) (Math.random() * 94) + 33);
            sb.append(randomChar);
        }

        //Getting the total of cart
        List<Cart> allCartItems = cartService.getCartItemsByUser();
        Integer total = 0;

        for(Cart each: allCartItems){
            total += each.getQuantity() * each.getProduct().getPrice();
        }



        //Adding it as order no
        newOrder.setOrderNo(sb.toString());

        newOrder.setShipment(shipment);
        newOrder.setUser(userRepository.getUSerByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        newOrder.setStatus("Order Created");
        newOrder.setTotalPrice(total);

        return orderRepo.save(newOrder);
    }

    @Override
    public Order directOrder(Integer id) {
        return null;
    }
}
