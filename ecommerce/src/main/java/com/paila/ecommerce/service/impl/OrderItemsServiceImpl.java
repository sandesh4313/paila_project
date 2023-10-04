package com.paila.ecommerce.service.impl;

import com.paila.ecommerce.entity.Cart;
import com.paila.ecommerce.entity.Order;
import com.paila.ecommerce.entity.OrderItems;
import com.paila.ecommerce.repository.OrderItemsRepo;
import com.paila.ecommerce.service.CartService;
import com.paila.ecommerce.service.OrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemsRepo orderItemsRepo;
    private final CartService cartService;

    @Override
    public List<OrderItems> generateOrderItems(Order order) {
        List<OrderItems> returnList = new ArrayList<>();

//        Getting all the items in the cart
        List<Cart> foundCartItems = cartService.getCartItemsByUser();

        //Save each item in the cart to the order
        for(Cart each: foundCartItems){
            OrderItems newOrderItem = new OrderItems();
            newOrderItem.setPrice(each.getProduct().getPrice());
            newOrderItem.setQuantity(each.getQuantity());
            newOrderItem.setProduct(each.getProduct());
            newOrderItem.setOrder(order);
            //Save the item to Order
            returnList.add(orderItemsRepo.save(newOrderItem));
            //Then delete it from cart
            cartService.deleteCartById(each.getId());
        }
        return returnList;
    }

//    @Override
//    public List<OrderItems> getAllOrderItemsOfUser(Integer orderId) {
//        return null;
//    }
}
