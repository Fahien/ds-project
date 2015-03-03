package me.fahien.ds.priorityqueue;

import java.util.Comparator;

import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.Entry;

public class HeapPriorityQueue<Key, Value> implements PriorityQueue<Key, Value> {

	public HeapPriorityQueue (Key keys[], Value values[], Comparator<Key> comparator) {
		// TODO
	}

	@Override
	public int size () {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty () {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entry<Key, Value> min () throws EmptyPriorityQueueException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<Key, Value> insert (Key key, Value value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<Key, Value> removeMin () throws EmptyPriorityQueueException {
		// TODO Auto-generated method stub
		return null;
	}
}