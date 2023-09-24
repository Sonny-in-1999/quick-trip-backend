package com.neurotoxin.quicktrip.repository;

import com.neurotoxin.quicktrip.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
}
