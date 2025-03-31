package com.akshay.liftmanagementsystem.entity;

import com.akshay.liftmanagementsystem.model.LiftDirections;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
 public class Lift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="total_floors",nullable = false)
	private int totalFloors;
	
	@Column(name="max_capacity",nullable = false)
	private int maximumCapacity;
	
	@Column(name="current_floor",nullable = false)
	private int currentFloor = 1;
	
	@Enumerated(EnumType.STRING)
	@Column(name="direction",nullable = false)
	 @JsonProperty("liftDirection")
	private LiftDirections liftDirection = LiftDirections.IDLE;
	
	@Column(name="current_occupancy",nullable = false)
	private int currentOccupancy = 0;

	public Lift() {
		super();
	}

	public Lift(Long id, int totalFloors, int maximumCapacity, int currentFloor, LiftDirections liftDirection,
			int currentOccupancy) {
		super();
		this.id = id;
		this.totalFloors = totalFloors;
		this.maximumCapacity = maximumCapacity;
		this.currentFloor = currentFloor;
		this.liftDirection = liftDirection;
		this.currentOccupancy = currentOccupancy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(int totalFloors) {
		this.totalFloors = totalFloors;
	}

	public int getMaximumCapacity() {
		return maximumCapacity;
	}

	public void setMaximumCapacity(int maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public LiftDirections getLiftDirection() {
		return liftDirection;
	}

	public void setLiftDirection(LiftDirections liftDirection) {
		this.liftDirection = liftDirection;
	}

	public int getCurrentOccupancy() {
		return currentOccupancy;
	}

	public void setCurrentOccupancy(int currentOccupancy) {
		this.currentOccupancy = currentOccupancy;
	}
	


}
