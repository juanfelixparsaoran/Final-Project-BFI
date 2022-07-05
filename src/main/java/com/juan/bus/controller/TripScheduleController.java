package com.juan.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.bus.models.TripSchedule;
import com.juan.bus.payload.request.GetTripScheduleRequest;
import com.juan.bus.repository.TripScheduleRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/tripschedule")
public class TripScheduleController {
	@Autowired
	TripScheduleRepository tripScheduleRepository;
	
	@PostMapping("/filter")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getTripBetweenTwoStop(@Valid @RequestBody GetTripScheduleRequest tripScheduleRequest ) {
		List<TripSchedule> tripSchedule = tripScheduleRepository.findTripScheduleByDate(tripScheduleRequest.getTripDate());
		return ResponseEntity.ok(tripSchedule);
	}
	
}


