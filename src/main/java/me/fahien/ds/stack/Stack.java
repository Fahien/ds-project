package me.fahien.ds.stack;

import me.fahien.ds.exception.EmptyStackException;

/** A collection of objects that are inserted and removed
 * according to the last-in first-out principle.
 * @author Fahien */
public interface Stack<E> {
	/** Returns the number of elements in the stack
	 * @return number of elements */
	int size();

	/** Tests whether the stack is empty
	 * @return true if the stack is empty */
	 boolean isEmpty();

	/**Returns, but does not remove, the element at the top of the stack
	 * @return top element
	 * @throws EmptyStackException if empty */
	E top() throws EmptyStackException;

	/** Inserts an element at the top of the stack
	 * @param element The element to be inserted */
	void push(E element);

	/**Removes and returns the top element from the stack
	 * @return the element removed
	 * @throws EmptyStackException if empty */
	E pop() throws EmptyStackException;
}