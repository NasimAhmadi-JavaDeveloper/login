package com.example.login.repository;

import com.example.login.model.entity.shoppingcart.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}