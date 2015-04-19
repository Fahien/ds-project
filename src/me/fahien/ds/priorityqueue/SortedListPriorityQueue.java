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

public class SortedListPriorityQueue<Key, Value> implements PriorityQueue<Key, Value> {
	protected PositionList<IEntry<Key, Value>> list;
	protected Comparator<Key> comparator;

	public SortedListPriorityQueue () {
		list = new NodePositionList<>();
		comparator = new DefaultComparator<>();
	}

	public SortedListPriorityQueue (Comparator<Key> comparator) {
		list = new NodePositionList<>();
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
		return list.size();
	}

	@Override
	public boolean isEmpty () {
		return list.isEmpty();
	}

	@Override
	public IEntry<Key, Value> min () throws EmptyPriorityQueueException {
		if (list.isEmpty()) {
			throw new EmptyPriorityQueueException ("The priority queue is empty");
		}
		return list.first().getElement();
	}

	@Override
	public IEntry<Key, Value> insert (Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		IEntry<Key, Value> entry = new Entry<>(key, value);
		insertEntry(entry);
		return entry;
	}

	protected Position<IEntry<Key, Value>> insertEntry (IEntry<Key, Value> entry) {
		if(list.isEmpty()) {
			list.addFirst(entry);
			return list.first();
		}
		else if (comparator.compare(entry.getKey(), list.last().getElement().getKey()) > 0) {
			list.addLast(entry);
			return list.last();
		}
		else {
			Position<IEntry<Key, Value>> position = list.first();
			while (comparator.compare(entry.getKey(), position.getElement().getKey()) > 0) {
				position = list.next(position);
			}
			list.addBefore(position, entry);
			return position;
		}
	}

	@Override
	public IEntry<Key, Value> removeMin () throws EmptyPriorityQueueException {
		if (list.isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		return list.remove(list.first());
	}
}