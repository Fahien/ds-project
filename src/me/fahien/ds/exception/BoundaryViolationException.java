package me.fahien.ds.exception;

public class BoundaryViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BoundaryViolationException (String message) {
		super(message);
	}
}