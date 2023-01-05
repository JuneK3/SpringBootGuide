package com.rootlab.ch9.data.repository;

import com.rootlab.ch9.data.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
