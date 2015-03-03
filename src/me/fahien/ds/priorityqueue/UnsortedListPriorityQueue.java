package me.fahien.ds.priorityqueue;

import java.util.Comparator;

import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.nodelist.NodeList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.comparator.DefaultComparator;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.composition.MyEntry;
import me.fahien.ds.util.position.Position;

public class UnsortedListPriorityQueue<Key, Value> implements PriorityQueue<Key, Value> {
	protected PositionList<Entry<Key, Value>> entries;
	protected Comparator<Key> comparator;

	public UnsortedListPriorityQueue () {
		entries = new NodeList<Entry<Key, Value>>();
		comparator = new DefaultComparator<Key>();
	}

	public UnsortedListPriorityQueue (Comparator<Key> comparator) {
		entries = new NodeList<Entry<Key, Value>>();
		this.comparator = comparator;
	}

	protected boolean checkKey (Key key) {
		try {
			return comparator.compare(key, key) == 0;
		} catch (ClassCastException e) {
			throw new InvalidKeyException("The key is not comparable");
		}
	}

	@Override
	public int size () {
		return entries.size();
	}

	@Override
	public boolean isEmpty () {
		return entries.isEmpty();
	}

	@Override
	public Entry<Key, Value> min () throws EmptyPriorityQueueException {
		if (entries.isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		return null;
	}

	@Override
	public Entry<Key, Value> insert (Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		Entry<Key, Value> entry = new MyEntry<Key, Value>(key, value);
		entries.addLast(entry);
		return entry;
	}

	@Override
	public Entry<Key, Value> removeMin () throws EmptyPriorityQueueException {
		if (entries.isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		Position<Entry<Key, Value>> position = entries.first();
		Position<Entry<Key, Value>> next = entries.next(position);
		while (comparator.compare(position.getElement().getKey(), next.getElement().getKey()) > 0 && next != null) {
			position = next;
			next = entries.next(next);
		}
		return entries.remove(position);
	}
}