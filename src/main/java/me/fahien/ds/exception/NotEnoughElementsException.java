package me.fahien.ds.exception;

public class NotEnoughElementsException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotEnoughElementsException() { super("The queue has not enough elements"); }
}
