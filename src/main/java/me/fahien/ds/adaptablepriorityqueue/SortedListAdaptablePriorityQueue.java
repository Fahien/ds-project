package me.fahien.ds.adaptablepriorityqueue;

import java.util.Comparator;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.priorityqueue.SortedListPriorityQueue;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.composition.LocationAwareEntry;
import me.fahien.ds.util.position.Position;

public class SortedListAdaptablePriorityQueue<Key, Value> extends SortedListPriorityQueue<Key, Value> implements AdaptablePriorityQueue<Key, Value> {

	public SortedListAdaptablePriorityQueue() {
		super();
	}

	public SortedListAdaptablePriorityQueue(Comparator<Key> comparator) {
		super(comparator);
	}

	/** Checks an entry to ensure it is location-aware */
	protected LocationAwareEntry<Key, Value> checkEntry(Entry<Key, Value> entry) throws InvalidEntryException {
		if (entry == null || !(entry instanceof LocationAwareEntry<?,?>)) 
			throw new InvalidEntryException("Invalid entry");
		return (LocationAwareEntry<Key, Value>) entry;
	}

	@Override public Entry<Key, Value> insert(Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		LocationAwareEntry<Key, Value> entry = new LocationAwareEntry<>(key, value);
		Position<Entry<Key, Value>> location = insertEntry(entry);
		entry.setLocation(location);
		return entry;
	}

	@Override public Entry<Key, Value> remove(Entry<Key, Value> entry) throws InvalidEntryException {
		LocationAwareEntry<Key, Value> locationAwareEntry = checkEntry(entry);
		Position<Entry<Key, Value>> position = locationAwareEntry.getLocation();
		return entries.remove(position);
	}

	@Override public Key replaceKey(Entry<Key, Value> entry, Key key) throws InvalidEntryException {
		checkKey(key);
		LocationAwareEntry<Key, Value> tempEntry = checkEntry(entry);
		remove(tempEntry);
		Key tempKey = tempEntry.getKey();
		tempEntry.setKey(key);
		insertEntry(tempEntry);
		return tempKey;
	}

	@Override public Value replaceValue(Entry<Key, Value> entry, Value value) throws InvalidEntryException {
		LocationAwareEntry<Key, Value> locationAwareEntry = checkEntry(entry);
		Value temp = locationAwareEntry.getValue();
		locationAwareEntry.setValue(value);
		return temp;
	}
}