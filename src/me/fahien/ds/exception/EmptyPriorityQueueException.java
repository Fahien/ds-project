package me.fahien.ds.exception;

public class EmptyPriorityQueueException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmptyPriorityQueueException (String message) {
		super(message);
	}
}