package com.paila.ecommerce;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class EcommerceApplication {

	@PostConstruct
	public void setup(){
		Stripe.apiKey="sk_test_51NShagL1DqlBaGLKi0AZRfwTvQeeGn1ECdjLdkuhCeMhYMHuSGuTE0QWary62jt6CQwWaXXXvrC4B76E31sDuV6l0037sgBr9c";
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
