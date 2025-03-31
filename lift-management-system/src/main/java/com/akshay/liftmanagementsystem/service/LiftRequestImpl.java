package com.akshay.liftmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akshay.liftmanagementsystem.dto.LiftRequestDTO;
import com.akshay.liftmanagementsystem.entity.LiftRequest;
import com.akshay.liftmanagementsystem.entity.User;
import com.akshay.liftmanagementsystem.model.RequestStatus;
import com.akshay.liftmanagementsystem.repository.LiftRequestRepository;
import com.akshay.liftmanagementsystem.repository.UserRepository;

@Service
public class LiftRequestImpl implements LiftRequestService{

    private final LiftRequestRepository liftRequestRepository;
    private final UserRepository userRepository;

    public LiftRequestImpl(LiftRequestRepository liftRequestRepository, UserRepository userRepository) {
        this.liftRequestRepository = liftRequestRepository;
        this.userRepository = userRepository;
    }

    public List<LiftRequestDTO> getAllPendingLiftRequests() {
    	return liftRequestRepository.findByRequestStatus(RequestStatus.PENDING)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private LiftRequestDTO convertToDTO(LiftRequest liftRequest) {
        LiftRequestDTO dto = new LiftRequestDTO();
        dto.setId(liftRequest.getId());
        dto.setUsername(liftRequest.getUser().getUsername()); 
        dto.setRequestedFloor(liftRequest.getRequestedFloor());
        dto.setRequestStatus(liftRequest.getRequestStatus());
        dto.setTimeStamp(liftRequest.getTimeStamp());
        return dto;
    }

    public List<LiftRequestDTO> getLiftRequestsByUser(String username) {
        return liftRequestRepository.findByUser_Username(username)
        		.stream()
        		.map(this::convertToDTO)
        		.collect(Collectors.toList());
    }
    
    @Transactional
    public LiftRequest createLiftRequest(String username, int requestedFloor, RequestStatus requestStatus, LocalDateTime timeStamp) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found: " + username);
        }
        
        // Create a new LiftRequest object
        LiftRequest liftRequest = new LiftRequest();
        liftRequest.setUser(userOptional.get());
        liftRequest.setRequestedFloor(requestedFloor);
        liftRequest.setRequestStatus(RequestStatus.PENDING); 
        liftRequest.setTimeStamp(LocalDateTime.now()); 

        return liftRequestRepository.save(liftRequest);
    }


    @Transactional
    public LiftRequest updateLiftRequestStatus(Long id, RequestStatus status) {
        Optional<LiftRequest> liftRequestOpt = liftRequestRepository.findById(id);
        if (liftRequestOpt.isPresent()) {
            LiftRequest liftRequest = liftRequestOpt.get();
            liftRequest.setRequestStatus(status);
            return liftRequestRepository.save(liftRequest);
        } else {
            throw new RuntimeException("Lift request not found");
        }
    }

    public List<LiftRequest> getAllLiftRequests() {
        return liftRequestRepository.findAll();
    }

    public Optional<LiftRequest> getLiftRequestById(Long id) {
        return liftRequestRepository.findById(id);
    }
    
    public void deleteLiftRequest(Long id) {
        liftRequestRepository.deleteById(id);
    }

}
