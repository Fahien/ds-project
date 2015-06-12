package me.fahien.ds.priorityqueue;

import java.util.Comparator;

import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.composition.PQEntry;
import me.fahien.ds.util.position.Position;

public class SortedListPriorityQueue<Key, Value> extends AbstractPriorityQueue<Key, Value> {
	protected PositionList<Entry<Key, Value>> entries = new NodePositionList<>();

	public SortedListPriorityQueue() {
		super();
	}

	public SortedListPriorityQueue(Comparator<Key> comparator) {
		super(comparator);
	}

	@Override public int size() {
		return entries.size();
	}

	@Override public Entry<Key, Value> min() throws EmptyPriorityQueueException {
		if (entries.isEmpty()) {
			throw new EmptyPriorityQueueException ("The priority queue is empty");
		}
		return entries.first().getElement();
	}

	@Override
	public Entry<Key, Value> insert(Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		Entry<Key, Value> entry = new PQEntry<>(key, value);
		insertEntry(entry);
		return entry;
	}

	protected Position<Entry<Key, Value>> insertEntry(Entry<Key, Value> entry) {
		if(entries.isEmpty()) {
			entries.addFirst(entry);
			return entries.first();
		}
		else if (compare(entry, entries.last().getElement()) > 0) {
			entries.addLast(entry);
			return entries.last();
		}
		else {
			Position<Entry<Key, Value>> position = entries.first();
			while (compare(entry, position.getElement()) > 0) {
				position = entries.next(position);
			}
			entries.addBefore(position, entry);
			return position;
		}
	}

	@Override public Entry<Key, Value> removeMin() throws EmptyPriorityQueueException {
		if (entries.isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		return entries.remove(entries.first());
	}
}