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
import com.juan.bus.models.User;
import com.juan.bus.payload.request.AgencyRequest;
import com.juan.bus.repository.AgencyRepository;
import com.juan.bus.repository.UserRepository;

import io.swagger.annotations.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/agency")
public class AgencyController {

	@Autowired
	AgencyRepository agencyRepository;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAgencyFromOwnerId(@PathVariable(value = "id") Long id) {
		User user = userRepository.findById(id).get();
		Agency agency = agencyRepository.findByOwner(user);
		return ResponseEntity.ok(agency);
	}

	@PostMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addAgencyByUserId(@PathVariable(value = "id") Long id,
			@Valid @RequestBody AgencyRequest agencyCustomRequest) {
		User user = userRepository.findById(id).get();
		Agency agency = new Agency(agencyCustomRequest.getCode(), agencyCustomRequest.getDetails(), agencyCustomRequest.getName(),user);
		return ResponseEntity.ok(agencyRepository.save(agency));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateAgencyByOwnerId(@PathVariable(value = "id") Long id,
			@Valid @RequestBody AgencyRequest agencyCustomRequest) {
		Agency agency = agencyRepository.findByOwnerUser(id);
		if (agency == null) {
			return ResponseEntity.notFound().build();
		}
		agency.setCode(agencyCustomRequest.getCode());
		agency.setDetails(agencyCustomRequest.getDetails());
		agency.setName(agencyCustomRequest.getName());

		Agency updatedAgency = agencyRepository.save(agency);

		return ResponseEntity.ok(updatedAgency);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> deleteAgencyById(@PathVariable(value = "id") Long id){
		Agency agency = agencyRepository.findById(id).get();
		if (agency == null) {
			return ResponseEntity.notFound().build();
		}
		
		agencyRepository.delete(agency);
		return ResponseEntity.ok(agency);
	}

}
