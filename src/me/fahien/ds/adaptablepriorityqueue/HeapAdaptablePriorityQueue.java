package me.fahien.ds.adaptablepriorityqueue;

import java.util.Comparator;

import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.priorityqueue.HeapPriorityQueue;
import me.fahien.ds.util.composition.IEntry;
import me.fahien.ds.util.composition.LocationAwareEntry;
import me.fahien.ds.util.position.Position;

public class HeapAdaptablePriorityQueue<Key, Value> extends HeapPriorityQueue<Key, Value> implements AdaptablePriorityQueue<Key, Value> {

	public HeapAdaptablePriorityQueue () {
		super();
	}

	public HeapAdaptablePriorityQueue (Comparator<Key> comparator) {
		super(comparator);
	}

	protected LocationAwareEntry<Key, Value> checkEntry(IEntry<Key, Value> entry) throws InvalidEntryException {
		if (entry == null || !(entry instanceof LocationAwareEntry<?,?>)) 
			throw new InvalidEntryException("Invalid entry");
		return (LocationAwareEntry<Key, Value>) entry;
	}

	protected Position<IEntry<Key, Value>> replaceEntry(Position<IEntry<Key, Value>> location, LocationAwareEntry<Key, Value> entry) {
		heap.replace(location, entry);
		Position<IEntry<Key, Value>> temp = entry.getLocation();
		entry.setLocation(location);
		return temp;
	}

	protected LocationAwareEntry<Key, Value> getEntry(Position<IEntry<Key, Value>> position) {
		return (LocationAwareEntry<Key, Value>) position.getElement();
	}

	@Override protected void swap(Position<IEntry<Key, Value>> positionX, Position<IEntry<Key, Value>> positionY) {
		super.swap(positionX, positionY);
		getEntry(positionX).setLocation(positionX);
		getEntry(positionY).setLocation(positionY);
	}

	@Override public IEntry<Key, Value> insert(Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		LocationAwareEntry<Key, Value> entry = new LocationAwareEntry<>(key, value);
		Position<IEntry<Key, Value>> position = heap.add(entry);
		entry.setLocation(position);
		upHeap(position);
		return entry;
	}

	@Override public IEntry<Key, Value> remove(IEntry<Key, Value> entry) throws InvalidEntryException {
		LocationAwareEntry<Key, Value> locationAwareEntry = checkEntry(entry);
		Position<IEntry<Key, Value>> location = locationAwareEntry.getLocation();
		try {
			if (size() == 1) {
				return heap.remove();
			}
			replaceEntry(location, (LocationAwareEntry<Key, Value>) heap.remove());
		} catch (EmptyTreeException e) {
			throw new InvalidEntryException("Invalid entry for an empty heap");
		}
		upHeap(location);
		downHeap(location);
		locationAwareEntry.setLocation(null);
		return locationAwareEntry;
	}

	@Override public Key replaceKey(IEntry<Key, Value> entry, Key key) throws InvalidEntryException {
		checkKey(key);
		LocationAwareEntry<Key, Value> locationAwareEntry = checkEntry(entry);
		Key temp = locationAwareEntry.getKey();
		locationAwareEntry.setKey(key);
		upHeap(locationAwareEntry.getLocation());
		downHeap(locationAwareEntry.getLocation());
		return temp;
	}

	@Override public Value replaceValue(IEntry<Key, Value> entry, Value value) throws InvalidEntryException {
		LocationAwareEntry<Key, Value> locationAwareEntry = checkEntry(entry);
		Value temp = locationAwareEntry.getValue();
		locationAwareEntry.setValue(value);
		return temp;
	}
}