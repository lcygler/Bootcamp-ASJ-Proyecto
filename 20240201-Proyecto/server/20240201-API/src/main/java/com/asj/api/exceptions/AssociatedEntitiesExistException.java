package com.asj.api.exceptions;

public class AssociatedEntitiesExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AssociatedEntitiesExistException(String message) {
		super(message);
	}
}
