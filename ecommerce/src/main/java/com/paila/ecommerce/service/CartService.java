package com.paila.ecommerce.service;

import com.paila.ecommerce.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    public void addToCart(Integer quantity,Integer productId);
    public void deleteCartById(Integer id);
    public List<Cart> getCartItemsByUser();
}
