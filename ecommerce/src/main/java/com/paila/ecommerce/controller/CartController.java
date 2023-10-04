package com.paila.ecommerce.controller;

import com.paila.ecommerce.entity.Cart;
import com.paila.ecommerce.entity.Product;
import com.paila.ecommerce.service.CartService;
import com.paila.ecommerce.service.CategoryService;
import com.paila.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CartService cartService;

    //    @GetMapping("/addToCart/{id}")
//    public String addToCart(@PathVariable int id, @RequestParam("quantity") Integer quantity) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        boolean isLoggedIn = authentication != null && authentication.isAuthenticated();
//
//        if(isLoggedIn){
//            cartService.addToCart(quantity, id);
//
//            return "redirect:/shop";
//        }else{
//            return "redirect:/login";
//        }
//
//    }
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, @RequestParam("quantity") Integer quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated();

        System.out.println("isLoggedIn: " + isLoggedIn); // Logging statement for debugging

        if (isLoggedIn) {
            cartService.addToCart(quantity, id);
            System.out.println("Cart item added"); // Logging statement for debugging
            return "redirect:/myCart";
        } else {
            System.out.println("User not authenticated. Redirecting to login"); // Logging statement for debugging
            return "redirect:/login";
        }
    }


    //    @GetMapping("/myCart")
//    public String getMyCartPage(Model model){
//        model.addAttribute("allCartItems", cartService.getCartItemsByUser());
//        model.addAttribute("allcategory", categoryService.getAllCategory());
//        return "mycartpage";
//    }
    @GetMapping("/myCart")
    public String getMyCartPage(Model model) {
        List<Cart> cartItems = cartService.getCartItemsByUser();
        double subtotal = 0.0;

        for (Cart cartItem : cartItems) {
            int quantity = cartItem.getQuantity();
            double price = cartItem.getProduct().getPrice();
            double itemTotal = quantity * price;
            subtotal += itemTotal;
        }

        double shippingCharge = subtotal * 0.02; // Calculate shipping charge as 2% of subtotal
        double total = subtotal + shippingCharge;

        // Add shipping cost or any additional charges here if applicable

        model.addAttribute("allCartItems", cartItems);
        model.addAttribute("allcategory", categoryService.getAllCategory());
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("total", total);
        model.addAttribute("shippingcharge", shippingCharge);
        return "mycartpage";
    }


    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable int id) {
        cartService.deleteCartById(id);
        return "redirect:/myCart";
    }
}
