package com.neurotoxin.quicktrip.repository;

import com.neurotoxin.quicktrip.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
