package me.fahien.ds.priorityqueue;

import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.Entry;

/** Interface for the priority queue ADT
 * @author Fahien */
public interface PriorityQueue<Key, Value> {
	/** Returns the number of elements in the queue */
	int size();

	/** Tests whether the queue is empty */
	boolean isEmpty();

	/** Returns, but does not remove, an {@code Entry<Key, Value>} having minimal key
	 * @return {@code Entry<Key, Value>} having minimal key
	 * @throws EmptyPriorityQueueException if the priority queue is empty  */
	Entry<Key, Value> min() throws EmptyPriorityQueueException;

	/** Creates an entry with this key and this value in the queue.
	 * @param key The key
	 * @param value The value */
	Entry<Key, Value> insert(Key key, Value value) throws InvalidKeyException;

	/** Removes and returns an {@code Entry<Key, Value>}
	 * having minimal key from the priority queue
	 * @return {@code Entry<Key, Value>} having minimal key
	 * @throws EmptyPriorityQueueException if the priority queue is empty */
	Entry<Key, Value> removeMin() throws EmptyPriorityQueueException;
}