package com.akshay.liftmanagementsystem.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.akshay.liftmanagementsystem.model.RequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lift_requests")
public class LiftRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="username", referencedColumnName = "username", nullable = false)
	private User user;
	
	@Column(name="lift_requests",nullable = false)
	private int requestedFloor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="request_status",nullable=false)
	private RequestStatus requestStatus = RequestStatus.PENDING;
	
	@Column(name="time_stamp",nullable = false , updatable = false)
	@CreationTimestamp
	private LocalDateTime timeStamp;
	
	

	public LiftRequest() {
		super();
	}

	public LiftRequest(Long id, User user, int requestedFloor, RequestStatus requestStatus, LocalDateTime timeStamp) {
		super();
		this.id = id;
		this.user = user;
		this.requestedFloor = requestedFloor;
		this.requestStatus = requestStatus;
		this.timeStamp = timeStamp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getRequestedFloor() {
		return requestedFloor;
	}



	public void setRequestedFloor(int requestedFloor) {
		this.requestedFloor = requestedFloor;
	}



	public RequestStatus getRequestStatus() {
		return requestStatus;
	}



	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}



	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}



	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	
	

}
