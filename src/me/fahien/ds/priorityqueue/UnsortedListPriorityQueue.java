package me.fahien.ds.priorityqueue;

import java.util.Comparator;

import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.comparator.DefaultComparator;
import me.fahien.ds.util.composition.IEntry;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.position.Position;

public class UnsortedListPriorityQueue<Key, Value> implements PriorityQueue<Key, Value> {
	protected PositionList<IEntry<Key, Value>> entries;
	protected Comparator<Key> comparator;

	public UnsortedListPriorityQueue() {
		entries = new NodePositionList<>();
		comparator = new DefaultComparator<>();
	}

	public UnsortedListPriorityQueue(Comparator<Key> comparator) {
		entries = new NodePositionList<>();
		this.comparator = comparator;
	}

	protected boolean checkKey(Key key) {
		try {
			return comparator.compare(key, key) == 0;
		} catch (ClassCastException e) {
			throw new InvalidKeyException("The key is not comparable");
		}
	}

	@Override public int size() {
		return entries.size();
	}

	@Override public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override public IEntry<Key, Value> min() throws EmptyPriorityQueueException {
		if (entries.isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		return null;
	}

	@Override public IEntry<Key, Value> insert(Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		IEntry<Key, Value> entry = new Entry<>(key, value);
		entries.addLast(entry);
		return entry;
	}

	@Override public IEntry<Key, Value> removeMin() throws EmptyPriorityQueueException {
		if (entries.isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		Position<IEntry<Key, Value>> position = entries.first();
		Position<IEntry<Key, Value>> next = entries.next(position);
		while (next != null && comparator.compare(position.getElement().getKey(), next.getElement().getKey()) > 0) {
			position = next;
			next = entries.next(next);
		}
		return entries.remove(position);
	}
}