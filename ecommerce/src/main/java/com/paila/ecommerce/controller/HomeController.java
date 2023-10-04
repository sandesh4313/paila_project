package com.paila.ecommerce.controller;

import com.paila.ecommerce.configuration.AuthenticationFacade;
import com.paila.ecommerce.entity.*;
import com.paila.ecommerce.repository.ProductRepository;
import com.paila.ecommerce.service.*;
//import com.paila.ecommerce.service.others.EmailService;
import com.paila.ecommerce.service.others.EmailService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    EmailService emailService;
    @Autowired
    OrderItemsService orderItemsService;
    @Autowired
    OrderService orderService;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShipmentService shipmentService;

    @Autowired
    CartService cartService;
    @Autowired
    AuthenticationFacade authenticationFacade;

    @GetMapping({"/", "/home"})
    public String home(@RequestParam(value = "keyword", required = false) String keyword, Model model) {

        if (keyword != null && !keyword.isEmpty()) {
            List<Product> searchResults = productService.searchProductsByName(keyword);
            model.addAttribute("searchResults", searchResults);
            return "test";
        }

        model.addAttribute("allcategory", categoryService.getAllCategory());
        model.addAttribute("allProducts", productService.getAllProduct());

        if (cartService.getCartItemsByUser().size() != 0) {
            model.addAttribute("allCartItems", cartService.getCartItemsByUser().size());
        }
        String username = authenticationFacade.getUsername();
        model.addAttribute("username", username);

        return "index";
    }


    @GetMapping("/get-products-by-category/{id}")
    public String getProductsByCategoryPage(@PathVariable("id") Integer categoryId, Model model) {
        model.addAttribute("allProducts", productService.getAllProductByCategoryId(categoryId));
        return "test";
    }


    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable Integer id) {
        model.addAttribute("categories", categoryService.getAllCategory());

        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("allcategory", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());

        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable Integer id) {

        model.addAttribute("product", productService.getProductById(id).get());
        return "viewProduct";
    }

    //    Redirect to single product's page'
    @GetMapping("/get-single-product/{id}")
    public String getSingleProductPage(@PathVariable("id") Integer productId,
                                       Model model) {
        model.addAttribute("product", productRepository.findById(productId).get());
        return "singleProductPage";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", new ContactForm());
        return "contact";
    }


    //Contact form controller
    @PostMapping("/send-contact-details")
    public String sendEmailOnContact(@ModelAttribute ContactForm contactForm) {
        String subject = "Contacted for Service";
        String message = "Dear Admin,+" +
                "You have received a contact from: \"" + contactForm.getName() + "\" with user email " +
                contactForm.getEmail() + "\"" + "with the message:\n\n+" +
                contactForm.getMessage();
        emailService.sendEmail("sandeshshrestha4313@gmail.com", subject, message);
        return "redirect:/";
    }

    @GetMapping("/success")
    public String paymentSuccess() {
        return "success";
    }

}
