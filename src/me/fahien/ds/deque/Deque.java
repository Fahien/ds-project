package me.fahien.ds.deque;

import me.fahien.ds.exception.EmptyDequeException;

/** Double-Ended Queue *
 * @author Fahien */
public interface Deque<E> {
	/** Returns the number of elements in the deque. */
	public int size();
	/** Tests whether the deque is empty. */
	public boolean isEmpty();
	/** Returns, but does not remove, the first element of the deque
	 * @throws EmptyDequeException whether the queue is empty */
	public E getFirst() throws EmptyDequeException;
	/** Returns, but does not remove, the last element of the deque
	 * @throws EmptyDequeException whether the queue is empty */
	public E getLast() throws EmptyDequeException;
	/** Inserts an element at the front of the deque.
	 * @param element The element */
	public void addFirst(E element);
	/** Inserts an element at the back of the deque.
	 * @param element The element */
	public void addLast(E element);
	/** Removes and returns the first element of the deque
	 * @return The first element
	 * @throws EmptyDequeException whether the queue is empty */
	public E removeFirst() throws EmptyDequeException;
	/** Removes and returns the last element of the deque
	 * @return The last element
	 * @throws EmptyDequeException whether the queue is empty */
	public E removeLast() throws EmptyDequeException; 
}