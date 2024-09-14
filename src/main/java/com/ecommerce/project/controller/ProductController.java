package com.ecommerce.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.DTO.ProductDTO;
import com.ecommerce.project.exceptions.ProductDoesNotExistException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.service.ProductService;

@RestController
public class ProductController {

  @Autowired
  private ProductService productServiceImp;

  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping("/products")
  public String showProductPage() {
    return "products.html";
  }

  @GetMapping("/admin/product")
  public ResponseEntity<List<Product>> getAllProducts() {
    try {
      List<Product> products = productServiceImp.getAllProducts();
      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (ProductDoesNotExistException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @PostMapping("/public/product")
  public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
    Product product = new Product();
    if (productDTO.getCategoryId() != null) {
      Category category = categoryRepository.findById(productDTO.getCategoryId())
          .orElseThrow(() -> new RuntimeException("Category Not Found"));
      product.setCategory(category);
    } else {
      product.setCategory(null);
    }
    productServiceImp.createProduct(product);
    return new ResponseEntity<>("Product Created", HttpStatus.CREATED);
  }

  @DeleteMapping("/public/product/{productId}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
    try {
      productServiceImp.deleteProduct(productId);
      return new ResponseEntity<>("Product is Deleted", HttpStatus.OK);
    } catch (ProductDoesNotExistException e) {
      return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }

  }

  @PutMapping("public/product/{productId}")
  public ResponseEntity<String> reduceQuantity(@PathVariable Long productId, @RequestBody Long quantity) {
    try {
      productServiceImp.reduceQuantity(productId, quantity);
      return new ResponseEntity<>("Product has been reduced", HttpStatus.OK);
    } catch (ProductDoesNotExistException e) {
      return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }
  }

}
