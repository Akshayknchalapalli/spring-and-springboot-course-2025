package com.akshay.liftmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akshay.liftmanagementsystem.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	boolean existsByUsername(String username);
}
