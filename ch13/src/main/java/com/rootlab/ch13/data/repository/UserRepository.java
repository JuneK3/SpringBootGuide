package com.rootlab.ch13.data.repository;

import com.rootlab.ch13.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUid(String username);
}
