package me.fahien.ds.adaptablepriorityqueue;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.priorityqueue.PriorityQueue;
import me.fahien.ds.util.composition.Entry;

public interface AdaptablePriorityQueue<Key, Value> extends PriorityQueue<Key, Value> {

	Entry<Key, Value> remove(Entry<Key, Value> entry) throws InvalidEntryException;

	Key replaceKey(Entry<Key, Value> entry, Key key) throws InvalidEntryException;

	Value replaceValue(Entry<Key, Value> entry, Value value) throws InvalidEntryException;
}