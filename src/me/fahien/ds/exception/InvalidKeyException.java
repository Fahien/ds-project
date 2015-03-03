package me.fahien.ds.exception;

public class InvalidKeyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidKeyException (String message) {
		super(message);
	}
}