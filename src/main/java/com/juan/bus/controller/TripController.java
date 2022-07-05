package com.juan.bus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.bus.models.Agency;
import com.juan.bus.models.Bus;
import com.juan.bus.models.Stop;
import com.juan.bus.models.Trip;
import com.juan.bus.payload.request.TripRequest;
import com.juan.bus.repository.AgencyRepository;
import com.juan.bus.repository.BusRepository;
import com.juan.bus.repository.StopRepository;
import com.juan.bus.repository.TripRepository;

import io.swagger.annotations.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/trip")
public class TripController {
	@Autowired
	TripRepository tripRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	StopRepository stopRepository;

	@PostMapping("/")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripRequest tripRequest) {
		Agency agency = agencyRepository.findById(tripRequest.getAgencyId()).get();
		Bus bus = busRepository.findById(tripRequest.getBusId()).get();
		Stop sourceStop = stopRepository.findById(tripRequest.getSourceStopId()).get();
		Stop destStop = stopRepository.findById(tripRequest.getDestStopId()).get();
		Trip trip = new Trip(tripRequest.getFare(), tripRequest.getJourneyTime(), sourceStop, destStop, bus, agency);
		return ResponseEntity.ok(tripRepository.save(trip));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getTripByAgencyId(@PathVariable(value = "id") Long id) {
		List<Trip> trip = tripRepository.findByAgencyId(id);
		return ResponseEntity.ok(trip);
	}
	
	@GetMapping("/{id_stop1}/{id_stop2}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getTripBetweenTwoStop(@PathVariable(value = "id_stop1") Long id_stop1,@PathVariable(value = "id_stop2") Long id_stop2 ) {
		List<Trip> trip = tripRepository.findTripsByStops(id_stop1,id_stop2);
		return ResponseEntity.ok(trip);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateTripById(@PathVariable(value = "id") Long id,
			@Valid @RequestBody TripRequest requestTrip) {
		Trip trip = tripRepository.findById(id).get();
		if (trip == null) {
			return ResponseEntity.notFound().build();
		}
		trip.setFare(requestTrip.getFare());
		trip.setJourneyTime(requestTrip.getJourneyTime());

		Trip updatedTrip = tripRepository.save(trip);

		return ResponseEntity.ok(updatedTrip);
	}
	
}

