package com.akshay.liftmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshay.liftmanagementsystem.entity.Lift;


@Repository
public interface LiftRepository extends JpaRepository<Lift, Long> {
	Lift findTopByOrderByIdAsc();
}
