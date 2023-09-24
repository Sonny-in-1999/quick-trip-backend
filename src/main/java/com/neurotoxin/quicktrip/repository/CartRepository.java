package com.neurotoxin.quicktrip.repository;

import com.neurotoxin.quicktrip.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CartRepository extends JpaRepository<Cart, Long> {
}
