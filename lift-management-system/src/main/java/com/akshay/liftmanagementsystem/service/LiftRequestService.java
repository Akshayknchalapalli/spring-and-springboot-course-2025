package com.akshay.liftmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.akshay.liftmanagementsystem.dto.LiftRequestDTO;
import com.akshay.liftmanagementsystem.entity.LiftRequest;
import com.akshay.liftmanagementsystem.model.RequestStatus;

public interface LiftRequestService {
	LiftRequest createLiftRequest(String username ,int requestedFloor , RequestStatus requestStatus ,  LocalDateTime timeStampl);
	List<LiftRequestDTO> getAllPendingLiftRequests();
	List<LiftRequestDTO> getLiftRequestsByUser(String username);
	LiftRequest updateLiftRequestStatus(Long id , RequestStatus status);
	List<LiftRequest> getAllLiftRequests();
	Optional<LiftRequest> getLiftRequestById(Long id);
	void deleteLiftRequest(Long id) ;
}
