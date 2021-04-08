package com.example.libman.dto;

import java.util.List;

import com.example.libman.domain.rental.Rental;

public class RentalListWrapper extends ListWrapper<Rental> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5922992927474261498L;

	public RentalListWrapper() {
		super();
	}

	public RentalListWrapper(List<Rental> rentals) {
		super(rentals);
	}

	public RentalListWrapper(Iterable<Rental> rentals) {
		super(rentals);
	}

}
