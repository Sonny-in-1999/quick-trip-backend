package com.neurotoxin.quicktrip.repository;

import com.neurotoxin.quicktrip.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
