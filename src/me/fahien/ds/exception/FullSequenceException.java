package me.fahien.ds.exception;

public class FullSequenceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FullSequenceException (String message) {
		super(message);
	}
}