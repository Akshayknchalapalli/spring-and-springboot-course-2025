package com.akshay.liftmanagementsystem.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.liftmanagementsystem.dto.LiftRequestDTO;
import com.akshay.liftmanagementsystem.entity.LiftRequest;
import com.akshay.liftmanagementsystem.model.RequestStatus;
import com.akshay.liftmanagementsystem.service.LiftRequestService;

@RestController
@RequestMapping("/api/lift-requests")
public class LiftRequestController {

    private final LiftRequestService liftRequestService;

    public LiftRequestController(LiftRequestService liftRequestService) {
        this.liftRequestService = liftRequestService;
    }

    @GetMapping("/pending")
    public List<LiftRequestDTO> getPendingRequests() {
        return liftRequestService.getAllPendingLiftRequests();
    }

    @GetMapping("/user")
    public List<LiftRequestDTO> getUserRequests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return liftRequestService.getLiftRequestsByUser(username);
    }

    @PostMapping("/create")
    public LiftRequestDTO createLiftRequest(@RequestBody LiftRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Ignore incoming ID from request and create a new LiftRequest
        LiftRequest liftRequest = liftRequestService.createLiftRequest(username, request.getRequestedFloor(), request.getRequestStatus(), request.getTimeStamp());

        return new LiftRequestDTO(liftRequest);  
    }


    @PutMapping("/{id}/update-status/{status}")
    public LiftRequest updateLiftRequestStatus(@PathVariable Long id, @PathVariable RequestStatus status) {
        return liftRequestService.updateLiftRequestStatus(id, status);
    }
}

