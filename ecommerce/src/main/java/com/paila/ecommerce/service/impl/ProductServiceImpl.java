package com.paila.ecommerce.service.impl;

import com.paila.ecommerce.entity.Product;
import com.paila.ecommerce.repository.ProductRepository;
import com.paila.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer id) {
    productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProductByCategoryId(Integer id) {
        return productRepository.findAllByCategory_Id(id);
    }
    public List<Product> searchProductsByName(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }


}
