package com.juan.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juan.bus.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
