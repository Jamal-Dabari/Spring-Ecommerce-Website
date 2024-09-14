package com.ecommerce.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.project.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findById(Long categoryId);

}
