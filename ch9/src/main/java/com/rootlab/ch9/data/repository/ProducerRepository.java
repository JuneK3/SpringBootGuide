package com.rootlab.ch9.data.repository;

import com.rootlab.ch9.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
