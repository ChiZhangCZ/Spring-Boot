package com.chi.zhang.springboot.ChiVD.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class DVDAlreadyRentedException extends RuntimeException {
	
	private Object dvdId;
	private Object rentedBy;
	
	public DVDAlreadyRentedException(Object dvdId, Object rentedBy) {
		super(String.format("%s is currently rented by %s", dvdId,rentedBy));
		this.dvdId = dvdId;
		this.rentedBy = rentedBy;
	}

	public Object getDvdId() {
		return dvdId;
	}

	public void setDvdId(Object dvdId) {
		this.dvdId = dvdId;
	}

	public Object getRentedBy() {
		return rentedBy;
	}

	public void setRentedBy(Object rentedBy) {
		this.rentedBy = rentedBy;
	}

}
