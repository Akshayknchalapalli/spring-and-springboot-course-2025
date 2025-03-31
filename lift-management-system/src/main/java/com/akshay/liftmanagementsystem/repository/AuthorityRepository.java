package com.akshay.liftmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akshay.liftmanagementsystem.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}

