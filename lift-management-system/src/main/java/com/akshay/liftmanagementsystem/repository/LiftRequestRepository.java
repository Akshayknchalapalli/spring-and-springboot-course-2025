package com.akshay.liftmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshay.liftmanagementsystem.entity.LiftRequest;
import com.akshay.liftmanagementsystem.model.RequestStatus;

public interface LiftRequestRepository extends JpaRepository<LiftRequest, Long> {
	List<LiftRequest> findByRequestStatus(RequestStatus requestStatus);
	List<LiftRequest> findByUser_Username(String username);

}
