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

public class SortedListPriorityQueue<Key, Value> implements PriorityQueue<Key, Value> {
	private PositionList<Entry<Key, Value>> entries;
	private Comparator<Key> comparator;

	public SortedListPriorityQueue () {
		entries = new NodeList<Entry<Key, Value>>();
		comparator = new DefaultComparator<Key>();
	}

	public SortedListPriorityQueue (Comparator<Key> comparator) {
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
		if (entries.isEmpty()) {
			throw new EmptyPriorityQueueException ("The priority queue is empty");
		}
		return entries.first().getElement();
	}

	@Override
	public Entry<Key, Value> insert (Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		Entry<Key, Value> entry = new MyEntry<Key, Value>(key, value);
		insertEntry(entry);
		return entry;
	}

	protected void insertEntry (Entry<Key, Value> entry) {
		if(entries.isEmpty()) {
			entries.addFirst(entry);
		}
		else if (comparator.compare(entry.getKey(), entries.last().getElement().getKey()) > 0) {
			entries.addLast(entry);
		}
		else {
			Position<Entry<Key, Value>> position = entries.first();
			while (comparator.compare(entry.getKey(), position.getElement().getKey()) > 0) {
				position = entries.next(position);
			}
			entries.addBefore(position, entry);
		}
	}

	@Override
	public Entry<Key, Value> removeMin () throws EmptyPriorityQueueException {
		if (entries.isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		return entries.remove(entries.first());
	}
}