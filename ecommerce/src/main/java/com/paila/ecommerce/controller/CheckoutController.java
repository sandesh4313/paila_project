package com.paila.ecommerce.controller;

import com.paila.ecommerce.entity.Cart;
import com.paila.ecommerce.entity.Order;
import com.paila.ecommerce.entity.OrderItems;
import com.paila.ecommerce.entity.Shipment;
import com.paila.ecommerce.service.*;
import com.paila.ecommerce.service.others.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CheckoutController {
    private final ShipmentService shipmentService;
    private final OrderItemsService orderItemsService;
    private final CartService cartService;
    private final OrderService orderService;
    @Autowired
    EmailService emailService;
    @Autowired
    CategoryService categoryService;

    //Mapping for checkout
    @GetMapping("/checkout")
    public String getCheckoutPage(Model model,HttpSession session) {
        model.addAttribute("allcategory", categoryService.getAllCategory());
        model.addAttribute("shipment", new Shipment());

        List<Cart> allCartItems = cartService.getCartItemsByUser();
        double subtotal = 0.0;

        for (Cart each : allCartItems) {
            subtotal += each.getQuantity() * each.getProduct().getPrice();
        }

        double shippingCharge = subtotal * 0.02; // Calculate shipping charge as 2% of subtotal
        double totalPrice = subtotal + shippingCharge;

        model.addAttribute("productName", "Nike Air Force 1 '07");
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("shippingCharge", shippingCharge);


        session.setAttribute("totalPrice", totalPrice);
        return "checkout";
    }




    @GetMapping("/get-add-address-form")
    public String getAddAddressForm(Model model){
        model.addAttribute("detail", new Shipment());
        return "demo/detailsForm";
    }

    @PostMapping("/get-add-address-form")
    public String saveData(@ModelAttribute Shipment dto){
        shipmentService.saveShipmentToTable(dto);
        return "redirect:/checkout";
    }

    //Delete the shipment address
//    Place this delete somewhere like in add form
    @GetMapping("/delete-shipping-address/{id}")
    public String deleteTheAddress(@PathVariable("id") Integer id){
        shipmentService.deleteShipmentDetails(id);
        return "redirect:/checkout";
    }

    @GetMapping("/set-order-items")
    public String getOrderItems(@ModelAttribute("createdOrder") Order createdOrder){
        List<OrderItems> orderItems = orderItemsService.generateOrderItems(createdOrder);
        String subject = "Order Confirmation";
        String message ="Your order has been placed!" + "your order number is " + orderItems.get(0).getOrder().getOrderNo() +
                "\n\n";

        //E-Mail pathaune bhaye yaha bhanda tala pathau email.
        for(OrderItems each: orderItems){
            message += each.getProduct().getName() + "\n";


        }
        message += "Thank you for Shopping With and Choosing Us!!";
        emailService.sendEmail(SecurityContextHolder.getContext().getAuthentication().getName(),subject,message);

        System.out.println("Order Done");
        return "redirect:/";
    }

    @PostMapping("/create-order")
    public String createOrderForUser(RedirectAttributes redirectAttributes,
                                     @ModelAttribute("shipment") Shipment shipment){

        Order createdOrder = orderService.saveOrderToTable(shipmentService.saveShipmentToTable(shipment));
        redirectAttributes.addAttribute("createdOrder", createdOrder);
        return "redirect:/set-order-items";

    }

}
