package com.paila.ecommerce.controller;

import com.paila.ecommerce.dto.CreatePayment;
import com.paila.ecommerce.dto.CreatePaymentResponse;
import com.paila.ecommerce.service.ProductService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class PaymentController {
    @Autowired
    ProductService productService;


    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment,
                                                     HttpSession session) throws StripeException {
        double total = (double) session.getAttribute("totalPrice");

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("npr")
                .setAmount((long) (total * 100L))
                .build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());
    }
}


