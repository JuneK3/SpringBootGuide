package com.rootlab.ch8.data.repository.support;

import com.rootlab.ch8.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
