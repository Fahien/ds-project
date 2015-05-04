package me.fahien.ds.priorityqueue;

import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.IEntry;

public interface PriorityQueue<Key, Value> {
	int size();
	boolean isEmpty();
	IEntry<Key, Value> min() throws EmptyPriorityQueueException;
	IEntry<Key, Value> insert(Key key, Value value) throws InvalidKeyException;
	IEntry<Key, Value> removeMin() throws EmptyPriorityQueueException;
}