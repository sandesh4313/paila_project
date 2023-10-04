package com.paila.ecommerce.service.impl;

import com.paila.ecommerce.entity.Cart;
import com.paila.ecommerce.entity.Product;
import com.paila.ecommerce.entity.User;
import com.paila.ecommerce.repository.CartRepository;
import com.paila.ecommerce.repository.ProductRepository;
import com.paila.ecommerce.repository.UserRepository;
import com.paila.ecommerce.service.CartService;
import com.paila.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void addToCart(Integer quantity,Integer productId) {
        Cart cart=new Cart();

        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUSerByEmail(email);
        Product product=productRepository.findById(productId).get();

        cart.setQuantity(quantity);
        cart.setUser(user);
        cart.setProduct(product);
        cartRepository.save(cart);

    }

    @Override
    public void deleteCartById(Integer id) {

        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> getCartItemsByUser() {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        return cartRepository.findByUser(userRepository.getUSerByEmail(email));


    }
}
