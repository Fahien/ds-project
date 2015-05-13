package me.fahien.ds.priorityqueue.adaptablepriorityqueue;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.priorityqueue.PriorityQueue;
import me.fahien.ds.util.composition.Entry;

/** This ADT extends the priority queue with additional functionality
 * @author Fahien */
public interface AdaptablePriorityQueue<Key, Value> extends PriorityQueue<Key, Value> {
	/** Removes this entry from the priority queue */
	Entry<Key, Value> remove(Entry<Key, Value> entry) throws InvalidEntryException;

	/** Replaces the key of this existing entry */
	Key replaceKey(Entry<Key, Value> entry, Key key) throws InvalidEntryException;

	/** Replaces the value of this existing entry */
	Value replaceValue(Entry<Key, Value> entry, Value value) throws InvalidEntryException;
}