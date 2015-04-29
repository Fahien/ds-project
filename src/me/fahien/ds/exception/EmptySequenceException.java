package me.fahien.ds.exception;

public class EmptySequenceException extends Exception {
	private static final long serialVersionUID = 1L;
	public EmptySequenceException() { super("The sequence is empty"); }
}