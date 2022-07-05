package com.juan.bus.controller;

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

import com.juan.bus.models.Stop;
import com.juan.bus.models.Ticket;
import com.juan.bus.models.TripSchedule;
import com.juan.bus.models.User;
import com.juan.bus.payload.request.TicketRequest;
import com.juan.bus.repository.TicketRepository;
import com.juan.bus.repository.TripScheduleRepository;
import com.juan.bus.repository.UserRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TripScheduleRepository tripScheduleRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	

	@PostMapping("/book")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> bookTicket(@Valid @RequestBody TicketRequest ticketRequest) {
		User user = userRepository.findById(ticketRequest.getPassegerId()).get();
		TripSchedule tripSchedule = tripScheduleRepository.findById(ticketRequest.getTripScheduleId()).get();
		
		
		Ticket ticket = new Ticket(ticketRequest.getSeatNumber(), ticketRequest.getCancellable(),
				ticketRequest.getJourneyDate(),tripSchedule,user);
		return ResponseEntity.ok(ticketRepository.save(ticket));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> updateTicket(@PathVariable(value = "id") Long id, @Valid @RequestBody TicketRequest requestTicket) {
		Ticket ticket = ticketRepository.findById(id).get();
		if (ticket == null) {
			return ResponseEntity.notFound().build();
		}
		ticket.setCancellable(requestTicket.getCancellable());
		ticket.setJourneyDate(requestTicket.getJourneyDate());
		ticket.setSeatNumber(requestTicket.getSeatNumber());

		Ticket updatedTicket = ticketRepository.save(ticket);

		return ResponseEntity.ok(updatedTicket);
	}
	
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> findAllTicket(@Valid @RequestBody TicketRequest ticketRequest) {
		return ResponseEntity.ok(ticketRepository.findAll());
	}
}
