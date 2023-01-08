package com.rootlab.ch13.data.repository;

import com.rootlab.ch13.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
