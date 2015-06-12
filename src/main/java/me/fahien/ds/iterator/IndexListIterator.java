package me.fahien.ds.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import me.fahien.ds.arraylist.ArrayIndexList;

public class IndexListIterator<E> implements Iterator<E> {
	private ArrayIndexList<E> array;
	private int index;

	public IndexListIterator(ArrayIndexList<E> array) {
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