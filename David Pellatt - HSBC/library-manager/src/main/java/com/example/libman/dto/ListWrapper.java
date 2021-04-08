package com.example.libman.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Data Transfer Object for wrapping collections for transmission from our REST
 * endpoints.
 * 
 * @param <E>
 */
public class ListWrapper<E> implements Serializable {
	private List<E> list;

	/**
	 * 
	 */
	private static final long serialVersionUID = -515770552580425231L;

	public ListWrapper() {
		super();
		list = new ArrayList<>();
	}

	public ListWrapper(Collection<E> collection) {
		this();

		collection.forEach(e -> list.add(e));
	}

	public ListWrapper(Iterable<E> iterable) {
		this();

		iterable.forEach(e -> list.add(e));
	}

	public List<E> getList() {
		return list;
	}
}
