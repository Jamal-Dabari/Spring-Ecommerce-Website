package com.ecommerce.project.service;

import java.util.List;

import com.ecommerce.project.DTO.ProductDTO;
import com.ecommerce.project.model.Product;

public interface ProductService {

  List<Product> getAllProducts();

  void createProduct(ProductDTO productDTO);

  void deleteProduct(Long productId);

  Product updateProduct(Product product, Long productId);

  void reduceQuantity(long productId, long quantity);

}
