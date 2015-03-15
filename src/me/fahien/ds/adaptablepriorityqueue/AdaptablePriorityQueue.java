package me.fahien.ds.adaptablepriorityqueue;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.priorityqueue.PriorityQueue;
import me.fahien.ds.util.composition.IEntry;

public interface AdaptablePriorityQueue<Key, Value> extends PriorityQueue<Key, Value> {
	public IEntry<Key, Value> remove (IEntry<Key, Value> entry) throws InvalidEntryException;
	public Key replaceKey (IEntry<Key, Value> entry, Key key) throws InvalidEntryException;
	public Value replaceValue (IEntry<Key, Value> entry, Value value) throws InvalidEntryException;
}