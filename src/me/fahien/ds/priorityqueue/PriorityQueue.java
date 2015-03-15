package me.fahien.ds.priorityqueue;

import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.IEntry;

public interface PriorityQueue<Key, Value> {
	public int size ();
	public boolean isEmpty ();
	public IEntry<Key, Value> min () throws EmptyPriorityQueueException;
	public IEntry<Key, Value> insert (Key key, Value value) throws InvalidKeyException;
	public IEntry<Key, Value> removeMin () throws EmptyPriorityQueueException;
}