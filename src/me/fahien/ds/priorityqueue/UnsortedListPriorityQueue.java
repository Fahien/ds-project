package me.fahien.ds.priorityqueue;

import java.util.Comparator;

import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.composition.PQEntry;
import me.fahien.ds.util.position.Position;

public class UnsortedListPriorityQueue<Key, Value> extends AbstractPriorityQueue<Key, Value> {
	private PositionList<Entry<Key, Value>> entries = new NodePositionList<>();

	public UnsortedListPriorityQueue() {
		super();
	}

	public UnsortedListPriorityQueue(Comparator<Key> comparator) {
		super(comparator);
	}

	@Override public int size() {
		return entries.size();
	}

	private Position<Entry<Key, Value>> findMin() throws EmptyPriorityQueueException {
		if (entries.isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		Position<Entry<Key, Value>> min = entries.first();
		for (Position<Entry<Key, Value>> position : entries.positions()) {
			if (compare(position.getElement(), min.getElement()) < 0) {
				min = position;
			}
		}
		return min;
	}

	@Override public Entry<Key, Value> min() throws EmptyPriorityQueueException {
		return findMin().getElement();
	}

	@Override public Entry<Key, Value> insert(Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		Entry<Key, Value> entry = new PQEntry<>(key, value);
		entries.addLast(entry);
		return entry;
	}

	@Override public Entry<Key, Value> removeMin() throws EmptyPriorityQueueException {
		return entries.remove(findMin());
	}
}