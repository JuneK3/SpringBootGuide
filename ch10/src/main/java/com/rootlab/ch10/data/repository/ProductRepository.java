package com.rootlab.ch10.data.repository;

import com.rootlab.ch10.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
