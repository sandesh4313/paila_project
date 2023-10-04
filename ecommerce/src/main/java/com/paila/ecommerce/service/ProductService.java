package com.paila.ecommerce.service;

import com.paila.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProduct();
    public void addProduct(Product product);
    public void deleteProductById(Integer id);
    public Optional<Product> getProductById(Integer id);
    public List<Product> getAllProductByCategoryId(Integer id);
    public List<Product> searchProductsByName(String keyword);


}
