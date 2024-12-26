package com.ecommerce.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project.DTO.ProductDTO;
import com.ecommerce.project.exceptions.ProductDoesNotExistException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

  List<Product> products = new ArrayList<>();
  private Long nextId = 1L;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public void createProduct(ProductDTO productDTO) {
    Category category = categoryRepository.findById(productDTO.getCategoryId())
        .orElseThrow(() -> new IllegalArgumentException("Category not found"));

    Product product = new Product();
    product.setProductName(productDTO.getProductName());
    product.setProductPrice(productDTO.getProductPrice());
    product.setProductQuantity(productDTO.getProductQuantity());
    product.setCategory(category);

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

  @Override
  public void reduceQuantity(long productId, long quantity) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductDoesNotExistException());

    if (product.getProductQuantity() < quantity) {
      throw new ProductDoesNotExistException("quantity error");
    }

    product.setProductQuantity(product.getProductQuantity() - quantity);
    productRepository.save(product);
  }

  public Optional<Product> LoadProductById(Long productId) throws ProductDoesNotExistException {
    return Optional
        .ofNullable(productRepository.findById(productId).orElseThrow(() -> new ProductDoesNotExistException()));
  }

}
