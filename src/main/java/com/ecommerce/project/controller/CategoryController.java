package com.ecommerce.project.controller;

import java.util.List;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class CategoryController {

  @Autowired
  private CategoryService categoryservice;

  @GetMapping("/public/categories")
  public List<Category> getAllCategories() {
    return categoryservice.getAllCategories();
  }

  @PostMapping("/public/categories")
  public String createCatagory(@RequestBody Category category) {
    categoryservice.createCategory(category);
    return "Category has been added successfully";
  }

  @DeleteMapping("/admin/categories/{categoryId}")
  public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
    try {
      String status = categoryservice.deleteCategory(categoryId);
      return new ResponseEntity<>(status, HttpStatus.OK);
      // return new ResponseEntity.ok(status);
      // return new ResponseEntity.status(HttpStatus.OK).body(status);
    } catch (ResponseStatusException e) {
      return new ResponseEntity<>(e.getReason(), e.getStatusCode());
    }
  }

  // @PutMapping("/public/categories/{categoryId}")
  @RequestMapping(value = "/public/categories/{categoryId}", method = RequestMethod.PUT)
  public ResponseEntity<String> updateCategories(@RequestBody Category category,
      @PathVariable Long categoryId) {
    try {
      Category savedCategory = categoryservice.updateCategory(category, categoryId);
      return new ResponseEntity<>("Category with category Id saved", HttpStatus.ACCEPTED);
    } catch (ResponseStatusException e) {
      return new ResponseEntity<>(e.getReason(), e.getStatusCode());
    }

  }

}
