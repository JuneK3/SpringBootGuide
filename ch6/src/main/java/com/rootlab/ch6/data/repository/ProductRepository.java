package com.rootlab.ch6.data.repository;

import com.rootlab.ch6.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
