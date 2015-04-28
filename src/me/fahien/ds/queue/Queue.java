package me.fahien.ds.queue;

import me.fahien.ds.exception.EmptyQueueException;

public interface Queue<E> {
	/** Returns the number of elements in the queue */
	public int size ();
	/** Tests whether the queue is empty. */
	public boolean isEmpty ();
	/** Returns, but does not remove, the first element of the queue
	 * @throws {@link me.fahien.ds.exception.EmptyQueueException} whether the queue is empty
	 * @return The first element */
	public E front() throws EmptyQueueException;
	/** Inserts an element at the rear of the queue.
	 * @param element The element */
	public void enqueue(E element);
	/** Removes and returns the first element of the queue
	 * @throws {@link me.fahien.ds.exception.EmptyQueueException} whether the queue is empty
	 * @return The first element */
	public E dequeue() throws EmptyQueueException;
}