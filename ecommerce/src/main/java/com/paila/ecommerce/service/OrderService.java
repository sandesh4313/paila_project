package com.paila.ecommerce.service;


import com.paila.ecommerce.entity.Order;
import com.paila.ecommerce.entity.Shipment;

public interface OrderService {

    //Save order to table
    Order saveOrderToTable(Shipment shipment);

    Order directOrder(Integer id);
}
