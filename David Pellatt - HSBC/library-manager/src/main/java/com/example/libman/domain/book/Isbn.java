package com.example.libman.domain.book;

import java.io.Serializable;

/**
 * We'll assume Isbn can have any valid Java long value.
 * 
 * @author David Pellatt
 */
public class Isbn implements Serializable {
	private long value;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8603351057248291735L;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Long.toString(value);
	}
}
