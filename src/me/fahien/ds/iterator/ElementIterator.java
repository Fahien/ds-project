package me.fahien.ds.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.position.Position;

public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> position;

	public ElementIterator () { }

	public ElementIterator (PositionList<E> list) {
		this.list = list;
		if (!list.isEmpty()) {
			position = list.first();
		}
	}

	@Override
	public boolean hasNext () {
		return position != null;
	}

	@Override
	public E next () throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException("No such position");
		E element = position.getElement();
		if (position == list.last()) {
			position = null;
		} else {
			position = list.next(position);
		}
		return element;
	}

	@Override
	public void remove () throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Remove");
	}
}