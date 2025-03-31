package com.akshay.liftmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.liftmanagementsystem.dto.LiftDTO;
import com.akshay.liftmanagementsystem.dto.OccupancyUpdateDTO;
import com.akshay.liftmanagementsystem.entity.Lift;
import com.akshay.liftmanagementsystem.service.LiftService;

@RestController
@RequestMapping("/api/lift")
public class LiftController {
	
	@Autowired
	private LiftService liftService;
	
	@PostMapping("/initialize")
    public Lift initializeLift(@RequestBody LiftDTO request) {
        return liftService.initializeLift(request.getTotalFloors(), request.getMaxCapacity() , request.getLiftDirection());
    }

    @PostMapping("/request/{floor}")
    public String requestLift(@PathVariable int floor) {
        return liftService.requestLift(floor);
    }
    
    @PostMapping("/update-occupancy")
    public ResponseEntity<String> updateOccupancy(@RequestBody OccupancyUpdateDTO request) {
        liftService.updateOccupancy(request.getOccupants());
        return ResponseEntity.ok("Occupancy updated successfully.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @GetMapping("/available-capacity")
    public int getAvailableCapacity() {
        return liftService.getAvailableCapacity();
    }

    @GetMapping("/status")
    public Lift getLiftStatus() {
        return liftService.getLiftStatus();
    }
}
