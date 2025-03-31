package com.akshay.liftmanagementsystem.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.akshay.liftmanagementsystem.entity.LiftRequest;
import com.akshay.liftmanagementsystem.model.RequestStatus;


public class LiftRequestDTO {
	private Long id;
	private String username;
	private Collection<? extends GrantedAuthority> userRole;
	private int requestedFloor;
	private RequestStatus requestStatus;
	private LocalDateTime timeStamp;
	
	public LiftRequestDTO() {
    }
	
	public LiftRequestDTO(LiftRequest liftRequest) {
        this.id = liftRequest.getId();
        this.username = liftRequest.getUser().getUsername(); 
        this.setUserRole(liftRequest.getUser().getAuthorities());
        this.requestedFloor = liftRequest.getRequestedFloor();
        this.requestStatus = liftRequest.getRequestStatus();
        this.timeStamp = liftRequest.getTimeStamp();
    }
	
	public int getRequestedFloor() { return requestedFloor; }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setRequestedFloor(int requestedFloor) { this.requestedFloor = requestedFloor; }

	public RequestStatus getRequestStatus() { return requestStatus; }
	public void setRequestStatus(RequestStatus requestStatus) { this.requestStatus = requestStatus; }

	public LocalDateTime getTimeStamp() { return timeStamp; }
	public void setTimeStamp(LocalDateTime timeStamp) { this.timeStamp = timeStamp; }

	public Collection<? extends GrantedAuthority> getUserRole() {
		return userRole;
	}

	public void setUserRole(Collection<? extends GrantedAuthority> userRole) {
		this.userRole = userRole;
	}
}

