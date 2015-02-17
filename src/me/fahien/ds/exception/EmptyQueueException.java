package me.fahien.ds.exception;

public class EmptyQueueException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptyQueueException (String message) {
		super(message);
	}
}