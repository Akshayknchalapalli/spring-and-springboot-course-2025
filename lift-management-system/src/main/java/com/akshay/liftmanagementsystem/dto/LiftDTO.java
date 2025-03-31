package com.akshay.liftmanagementsystem.dto;

import com.akshay.liftmanagementsystem.model.LiftDirections;

public class LiftDTO {
	private Long id;
    private int totalFloors;
    private int maxCapacity;
    private LiftDirections liftDirection; 

    // Getters and Setters

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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public LiftDirections getLiftDirection() {
        return liftDirection;
    }

    public void setLiftDirection(LiftDirections liftDirection) {
        this.liftDirection = liftDirection;
    }
}
