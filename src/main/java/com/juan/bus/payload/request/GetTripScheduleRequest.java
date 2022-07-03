package com.juan.bus.payload.request;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

public class GetTripScheduleRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	private int available_seats; 
	
	private Long trip_detail;
	
	private String tripDate;

	public GetTripScheduleRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetTripScheduleRequest(Long id, int available_seats, Long trip_detail, String tripDate) {
		super();
		this.id = id;
		this.available_seats = available_seats;
		this.trip_detail = trip_detail;
		this.tripDate = tripDate;
	}

	@Override
	public String toString() {
		return "GetTripScheduleRequest [id=" + id + ", available_seats=" + available_seats + ", trip_detail="
				+ trip_detail + ", tripDate=" + tripDate + ", hashCode()=" + hashCode() + ", getId()=" + getId()
				+ ", getAvailable_seats()=" + getAvailable_seats() + ", getTrip_detail()=" + getTrip_detail()
				+ ", getTripDate()=" + getTripDate() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(available_seats, id, tripDate, trip_detail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetTripScheduleRequest other = (GetTripScheduleRequest) obj;
		return available_seats == other.available_seats && Objects.equals(id, other.id)
				&& Objects.equals(tripDate, other.tripDate) && Objects.equals(trip_detail, other.trip_detail);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public Long getTrip_detail() {
		return trip_detail;
	}

	public void setTrip_detail(Long trip_detail) {
		this.trip_detail = trip_detail;
	}

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}
	
	
}
