package me.fahien.ds.exception;

public class InvalidEntryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidEntryException (String message) {
		super(message);
	}
}