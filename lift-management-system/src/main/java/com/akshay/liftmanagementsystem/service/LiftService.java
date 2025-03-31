package com.akshay.liftmanagementsystem.service;

import com.akshay.liftmanagementsystem.entity.Lift;
import com.akshay.liftmanagementsystem.model.LiftDirections;

public interface LiftService {
	Lift getLiftStatus();
	void updateOccupancy(int occupants);
	int getAvailableCapacity();
	Lift initializeLift(int totalFloors, int maxCapacity , LiftDirections liftDirection);
	String requestLift(int requestedFloor);
}
