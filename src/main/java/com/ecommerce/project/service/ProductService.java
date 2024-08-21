package com.ecommerce.project.service;

import java.util.List;

import com.ecommerce.project.model.Product;

public interface ProductService {

  List<Product> getAllProducts();

  void createProduct(Product product);

  void deleteProduct(Long productId);

  Product updateProduct(Product product, Long productId);

}
