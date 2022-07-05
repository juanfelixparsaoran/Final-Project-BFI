package com.juan.bus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.bus.models.Bus;
import com.juan.bus.models.Stop;
import com.juan.bus.payload.request.BusCustomRequest;
import com.juan.bus.repository.StopRepository;

import io.swagger.annotations.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/stop")
public class StopController {

	@Autowired
	StopRepository stopRepository;

	@GetMapping("/")
//	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getAllStops() {
		return ResponseEntity.ok(stopRepository.findAll());
	}

	@PostMapping("/")
//	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> addStop(@Valid @RequestBody Stop stop) {
		return ResponseEntity.ok(stopRepository.save(stop));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateStopById(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Stop requestStop) {
		Stop stop = stopRepository.findById(id).get();
		if (stop == null) {
			return ResponseEntity.notFound().build();
		}
		stop.setCode(requestStop.getCode());
		stop.setDetail(requestStop.getDetail());
		stop.setName(requestStop.getName());

		Stop updatedStop = stopRepository.save(stop);

		return ResponseEntity.ok(updatedStop);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> deleteStopById(@PathVariable(value = "id") Long id){
		Stop stop = stopRepository.findById(id).get();
		if (stop == null) {
			return ResponseEntity.notFound().build();
		}
		
		stopRepository.delete(stop);
		return ResponseEntity.ok(stop);
	}

}
