package me.fahien.ds.queue;

import me.fahien.ds.exception.EmptyQueueException;

public interface Queue<E> {
	/** Returns the number of elements in the queue */
	int size ();

	/** Tests whether the queue is empty. */
	boolean isEmpty ();

	/** Returns, but does not remove, the first element of the queue
	 * @throws EmptyQueueException whether the queue is empty
	 * @return The first element */
	E front() throws EmptyQueueException;

	/** Inserts an element at the rear of the queue.
	 * @param element The element */
	void enqueue(E element);

	/** Removes and returns the first element of the queue
	 * @throws EmptyQueueException whether the queue is empty
	 * @return The first element */
	E dequeue() throws EmptyQueueException;
}