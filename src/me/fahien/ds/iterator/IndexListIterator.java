package me.fahien.ds.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexListIterator<E> implements Iterator<E> {
	private E[] array;
	private int index;

	public IndexListIterator (E[] array) {
		this.array = array;
	}

	@Override
	public boolean hasNext () {
		return index < array.length - 1;
	}

	@Override
	public E next () throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException("No such element");
		return array[index++];
	}

	@Override
	public void remove () throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Remove");
	}
}