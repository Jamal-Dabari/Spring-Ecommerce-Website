package com.ecommerce.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {
  private List<Category> categories = new ArrayList<>();
  private Long nextId = 1L;

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public void createCategory(Category category) {
    category.setCategoryId(nextId++);
    category.setName(category.getName());
    categoryRepository.save(category);
  }

  @Override
  public String deleteCategory(Long categoryId) {
    List<Category> categories = categoryRepository.findAll();
    Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

    if (category == null) {
      return "category not found";
    }

    categoryRepository.delete(category);
    return "Category with category id: " + categoryId + " deleted succesfully";

  }

  @Override
  public Category updateCategory(Category category, Long categoryId) {
    Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);

    Category savedCategory = savedCategoryOptional
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
    Optional<Category> optionalCategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
        .findFirst();

    if (optionalCategory.isPresent()) {
      Category existingCategory = optionalCategory.get();
      existingCategory.setName(category.getName());
      savedCategory = categoryRepository.save(existingCategory);
      return savedCategory;
    } else
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");

  }

}
