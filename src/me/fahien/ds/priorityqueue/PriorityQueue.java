package me.fahien.ds.priorityqueue;

import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.Entry;

public interface PriorityQueue<Key, Value> {
	public int size ();
	public boolean isEmpty ();
	public Entry<Key, Value> min () throws EmptyPriorityQueueException;
	public Entry<Key, Value> insert (Key key, Value value) throws InvalidKeyException;
	public Entry<Key, Value> removeMin () throws EmptyPriorityQueueException;
}