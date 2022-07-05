package com.juan.bus.controller;

import java.util.List;
import java.util.Optional;

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

import com.juan.bus.models.Agency;
import com.juan.bus.models.Bus;
import com.juan.bus.payload.request.BusCustomRequest;
import com.juan.bus.repository.AgencyRepository;
import com.juan.bus.repository.BusRepository;

import io.swagger.annotations.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

	@Autowired
	BusRepository busRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getBusByAgencyId(@PathVariable(value = "id") Long id) {
		List<Bus> bus = busRepository.findByAgencyId(id);
		return ResponseEntity.ok(bus);
	}

	@PostMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addBusByUserId(@PathVariable(value = "id") Long id,
			@Valid @RequestBody BusCustomRequest busCustomRequest) {
		Agency agency = agencyRepository.findByOwnerUser(id);
		Bus bus = new Bus(busCustomRequest.getCode(), busCustomRequest.getCapacity(), busCustomRequest.getMake(),
				agency);
		return ResponseEntity.ok(busRepository.save(bus));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateBusById(@PathVariable(value = "id") Long id,
			@Valid @RequestBody BusCustomRequest busCustomRequest) {
		Bus bus = busRepository.findById(id).get();
		if (bus == null) {
			return ResponseEntity.notFound().build();
		}
		bus.setCode(busCustomRequest.getCode());
		bus.setCapacity(busCustomRequest.getCapacity());
		bus.setMake(busCustomRequest.getMake());

		Bus updatedBus = busRepository.save(bus);

		return ResponseEntity.ok(updatedBus);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> deleteBusById(@PathVariable(value = "id") Long id){
		Bus bus = busRepository.findById(id).get();
		if (bus == null) {
			return ResponseEntity.notFound().build();
		}
		
		busRepository.delete(bus);
		return ResponseEntity.ok(bus);
	}

}

