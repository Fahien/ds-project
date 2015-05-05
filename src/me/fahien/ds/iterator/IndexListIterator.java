package me.fahien.ds.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import me.fahien.ds.list.ArrayList;

public class IndexListIterator<E> implements Iterator<E> {
	private ArrayList<E> array;
	private int index;

	public IndexListIterator(ArrayList<E> array) {
		this.array = array;
		index = 0;
	}

	@Override public boolean hasNext() {
		return index < array.size();
	}

	@Override public E next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException("No such element");
		return array.get(index++);
	}

	@Override public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Remove");
	}
}