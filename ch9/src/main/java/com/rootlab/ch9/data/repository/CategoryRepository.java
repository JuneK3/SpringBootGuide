package com.rootlab.ch9.data.repository;

import com.rootlab.ch9.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
