package me.fahien.ds.adaptablepriorityqueue;

import java.util.Comparator;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.priorityqueue.SortedListPriorityQueue;
import me.fahien.ds.util.composition.IEntry;
import me.fahien.ds.util.composition.LocationAwareEntry;
import me.fahien.ds.util.position.Position;

public class SortedListAdaptablePriorityQueue<Key, Value> extends SortedListPriorityQueue<Key, Value> implements AdaptablePriorityQueue<Key, Value> {

	public SortedListAdaptablePriorityQueue() {
		super();
	}

	public SortedListAdaptablePriorityQueue(Comparator<Key> comparator) {
		super(comparator);
	}

	protected LocationAwareEntry<Key, Value> checkEntry(IEntry<Key, Value> entry) throws InvalidEntryException {
		if (entry == null || !(entry instanceof LocationAwareEntry<?,?>)) 
			throw new InvalidEntryException("Invalid entry");
		return (LocationAwareEntry<Key, Value>) entry;
	}

	@Override public IEntry<Key, Value> insert(Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		LocationAwareEntry<Key, Value> entry = new LocationAwareEntry<>(key, value);
		Position<IEntry<Key, Value>> location = insertEntry(entry);
		entry.setLocation(location);
		return entry;
	}

	@Override public IEntry<Key, Value> remove(IEntry<Key, Value> entry) throws InvalidEntryException {
		LocationAwareEntry<Key, Value> locationAwareEntry = checkEntry(entry);
		Position<IEntry<Key, Value>> position = locationAwareEntry.getLocation();
		return list.remove(position);
	}

	@Override public Key replaceKey(IEntry<Key, Value> entry, Key key) throws InvalidEntryException {
		checkKey(key);
		remove(checkEntry(entry));
		Key temp = entry.getKey();
		entry.setKey(key);
		insertEntry(entry);
		return temp;
	}

	@Override public Value replaceValue(IEntry<Key, Value> entry, Value value) throws InvalidEntryException {
		LocationAwareEntry<Key, Value> locationAwareEntry = checkEntry(entry);
		Value temp = locationAwareEntry.getValue();
		locationAwareEntry.setValue(value);
		return temp;
	}
}