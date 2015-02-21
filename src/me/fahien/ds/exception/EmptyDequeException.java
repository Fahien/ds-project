package me.fahien.ds.exception;

public class EmptyDequeException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptyDequeException (String message) {
		super(message);
	}
}