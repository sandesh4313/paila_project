package com.paila.ecommerce.service;



import com.paila.ecommerce.entity.Order;
import com.paila.ecommerce.entity.OrderItems;

import java.util.List;

public interface OrderItemsService {

    List<OrderItems> generateOrderItems(Order order);

//    List<OrderItems> getAllOrderItemsOfUser();
}
