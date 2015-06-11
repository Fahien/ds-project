package me.fahien.ds.priorityqueue;

import java.util.Comparator;

import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.comparator.DefaultComparator;
import me.fahien.ds.util.composition.Entry;

/** Abstract Priority Queue
 * @author Fahien */
public abstract class AbstractPriorityQueue<Key, Value> implements PriorityQueue<Key, Value> {
	private Comparator<Key> comparator;

	public AbstractPriorityQueue() {
		comparator = new DefaultComparator<>();
	}

	public AbstractPriorityQueue(Comparator<Key> comparator) {
		this.comparator = comparator;
	}

	protected int compare(Entry<Key, Value> a, Entry<Key, Value> b) {
		return comparator.compare(a.getKey(), b.getKey());
	}

	protected boolean checkKey(Key key) {
		try {
			return comparator.compare(key, key) == 0;
		} catch (ClassCastException e) {
			throw new InvalidKeyException("The key is not comparable");
		}
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}
}
