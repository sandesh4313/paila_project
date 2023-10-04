package com.paila.ecommerce.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.GeneratedValue;

    @Controller
    public class WevController {
        @GetMapping("/paynow")
        public String stripeHome(Model model){

            return "stripe";
        }
    }
