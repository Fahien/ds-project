package me.fahien.ds.exception;

public class UndeletableNodeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UndeletableNodeException (String message) {
		super(message);
	}
}