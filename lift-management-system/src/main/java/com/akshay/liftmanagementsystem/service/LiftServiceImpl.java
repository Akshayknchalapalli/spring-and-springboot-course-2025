package com.akshay.liftmanagementsystem.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import org.springframework.stereotype.Service;
import com.akshay.liftmanagementsystem.entity.Lift;
import com.akshay.liftmanagementsystem.model.LiftDirections;
import com.akshay.liftmanagementsystem.repository.LiftRepository;

@Service
public class LiftServiceImpl  implements LiftService{
	
	private final LiftRepository liftRepository;
	
	
	public LiftServiceImpl(LiftRepository liftRepository) {
		super();
		this.liftRepository = liftRepository;
	}

	@Override
	public Lift getLiftStatus() {
		return liftRepository.findTopByOrderByIdAsc();
	}

	@Override
    public String requestLift(int requestedFloor) {
        Optional<Lift> liftOpt = liftRepository.findById(1L);
        if (liftOpt.isEmpty()) return "Lift is not initialized.";

        Lift lift = liftOpt.get();

        if (requestedFloor < 1 || requestedFloor > lift.getTotalFloors()) {
            return "Invalid floor number!";
        }

        if (!floorRequestQueue.contains(requestedFloor)) {
            floorRequestQueue.add(requestedFloor);
            System.out.println("Added floor " + requestedFloor + " to the queue.");
        }
        
        if (lift.getCurrentFloor() < requestedFloor) {
            lift.setLiftDirection(LiftDirections.UP);
            System.out.println("Lift direction set to UP.");
        } else if (lift.getCurrentFloor() > requestedFloor) {
            lift.setLiftDirection(LiftDirections.DOWN);
            System.out.println("Lift direction set to DOWN.");
        } else {
            System.out.println("Lift is already at requested floor.");
            return "Lift is already at floor " + requestedFloor;
        }


        boolean stoppedForIntermediate = checkForIntermediateRequests(lift);

        if (!stoppedForIntermediate) {
        	System.out.println("Moving lift to floor: " + requestedFloor);
            moveLift(lift, requestedFloor);
        }

        return "Lift is moving to floor " + requestedFloor;
    }

	private void moveLift(Lift lift, int requestedFloor) {
		while(lift.getCurrentFloor() != requestedFloor) {
			try {
	            Thread.sleep(1000); // 1-second delay
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            System.out.println("Lift movement interrupted.");
	            return;
	        }
			if(lift.getLiftDirection() == LiftDirections.UP) {
				lift.setCurrentFloor(lift.getCurrentFloor() + 1);
			} else if (lift.getLiftDirection() == LiftDirections.DOWN) {
				lift.setCurrentFloor(lift.getCurrentFloor() - 1);
			}
			
			if (lift.getCurrentFloor() < 1 || lift.getCurrentFloor() > lift.getTotalFloors()) {
	            System.out.println("Lift is out of bounds. Stopping lift.");
	            lift.setLiftDirection(LiftDirections.IDLE);
	            liftRepository.save(lift);
	            return;
	        }
			Lift updatedLift = liftRepository.save(lift);
	        System.out.println("Lift moving... Current floor: " + updatedLift.getCurrentFloor());
            
            if (checkForIntermediateRequests(lift)) {
                return;
            }
		}
		lift.setLiftDirection(LiftDirections.IDLE);
        liftRepository.save(lift);
        System.out.println("Lift has stopped at floor: " + lift.getCurrentFloor());
	}

	private boolean checkForIntermediateRequests(Lift lift) {
		List<Integer> pendingRequests = getPendingRequests();
		if (pendingRequests.isEmpty()) {
	        System.out.println("No intermediate requests.");
	        return false;
	    }
		
		int currentFloor = lift.getCurrentFloor();
		int destinationFloor = lift.getLiftDirection() == LiftDirections.UP ? 
								Collections.min(pendingRequests) : Collections.max(pendingRequests);
		
		if ((lift.getLiftDirection() == LiftDirections.UP && destinationFloor > currentFloor) ||
		        (lift.getLiftDirection() == LiftDirections.DOWN && destinationFloor < currentFloor)) {
			lift.setCurrentFloor(destinationFloor);
			lift.setLiftDirection(LiftDirections.IDLE);
			liftRepository.save(lift);
			System.out.println("Intermediate stop at floor: " + destinationFloor);
	        return true;
		}
		System.out.println("No valid intermediate requests.");
		return false;
	}
	
	private static Queue<Integer> floorRequestQueue = new LinkedList<>();

    @Override
    public Lift initializeLift(int totalFloors, int maxCapacity , LiftDirections liftDirection) {
        Lift lift = new Lift();
        lift.setTotalFloors(totalFloors);
        lift.setMaximumCapacity(maxCapacity);
        lift.setLiftDirection(liftDirection);
        liftRepository.save(lift);
        return lift;
    }

	private List<Integer> getPendingRequests() {
		return new ArrayList<>(floorRequestQueue);
	}
	
	public void addFloorRequest(int floor) {
		if(!floorRequestQueue.contains(floor)) {
			floorRequestQueue.add(floor);
		}
	}

	@Override
	public void updateOccupancy(int occupants) {
		Lift lift = liftRepository.findTopByOrderByIdAsc();
		if(occupants > lift.getMaximumCapacity()) {
			throw new IllegalArgumentException("Exceeds maximum lift capacity! Maximum capacity is " + lift.getMaximumCapacity() + ".");
		}
		lift.setCurrentOccupancy(occupants);
        liftRepository.save(lift);
        
        int availableCapacity = getAvailableCapacity();
        System.out.println("Available capacity: " + availableCapacity);
	}

	@Override
	public int getAvailableCapacity() {
		Lift lift = liftRepository.findTopByOrderByIdAsc();
	    return lift.getMaximumCapacity() - lift.getCurrentOccupancy();
	}

}
