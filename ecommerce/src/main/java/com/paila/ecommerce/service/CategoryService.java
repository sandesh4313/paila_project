package com.paila.ecommerce.service;

import com.paila.ecommerce.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> getAllCategory();
    public void addCategory(Category category);
    public void deleteCategoryById(Integer id);
    public Optional<Category> getCategoryById(Integer id);
}
