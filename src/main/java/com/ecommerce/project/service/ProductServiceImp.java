package com.ecommerce.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project.exceptions.ProductDoesNotExistException;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.repositories.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

  List<Product> products = new ArrayList<>();
  private Long nextId = 1L;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public void createProduct(Product product) {
    product.setProductId(nextId++);
    productRepository.save(product);
  }

  @Override
  public void deleteProduct(Long productId) {
    Product product = productRepository.findById(productId).orElseThrow(() -> new ProductDoesNotExistException());
    productRepository.delete(product);
  }

  @Override
  public Product updateProduct(Product product, Long productId) {
    return product;
  }

}
