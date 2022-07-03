package com.juan.bus.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trip")
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int fare;
	
	private int journeyTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "source_stop_id")
	private Stop sourceStop;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dest_stop_id")
	private Stop destStop;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bus_id")
	private Bus bus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id")
	private Agency agency;

	public Trip() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trip(Long id, int fare, int journeyTime, Stop sourceStop, Stop destStop, Bus bus, Agency agency) {
		super();
		this.id = id;
		this.fare = fare;
		this.journeyTime = journeyTime;
		this.sourceStop = sourceStop;
		this.destStop = destStop;
		this.bus = bus;
		this.agency = agency;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", fare=" + fare + ", journeyTime=" + journeyTime + ", sourceStop=" + sourceStop
				+ ", destStop=" + destStop + ", bus=" + bus + ", agency=" + agency + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(agency, bus, destStop, fare, id, journeyTime, sourceStop);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trip other = (Trip) obj;
		return Objects.equals(agency, other.agency) && Objects.equals(bus, other.bus)
				&& Objects.equals(destStop, other.destStop) && fare == other.fare && Objects.equals(id, other.id)
				&& journeyTime == other.journeyTime && Objects.equals(sourceStop, other.sourceStop);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public int getJourneyTime() {
		return journeyTime;
	}

	public void setJourneyTime(int journeyTime) {
		this.journeyTime = journeyTime;
	}

	public Stop getSourceStop() {
		return sourceStop;
	}

	public void setSourceStop(Stop sourceStop) {
		this.sourceStop = sourceStop;
	}

	public Stop getDestStop() {
		return destStop;
	}

	public void setDestStop(Stop destStop) {
		this.destStop = destStop;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
}
