package com.example.login.repository;

import com.example.login.model.entity.shoppingcart.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}