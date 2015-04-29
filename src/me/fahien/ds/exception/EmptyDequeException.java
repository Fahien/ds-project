package me.fahien.ds.exception;

public class EmptyDequeException extends Exception {
	private static final long serialVersionUID = 1L;
	public EmptyDequeException() { super("The deque is empty"); }
}