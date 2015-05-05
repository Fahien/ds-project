package me.fahien.ds.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import me.fahien.ds.util.position.Node;

/** Linked iterator
 * @author Fahien */
public class LinkedIterator<E> implements Iterator<E> {
	private Node<E> head;
	private int size;

	public LinkedIterator(E[] array) {
		size = array.length;
		for (int i = size - 1; i >= 0; i--) {
			Node<E> node = new Node(array[i], head);
			head = node;
		}
	}

	/** Tests whether the iterator has a next object.
	 * @return true if there are further objects, false otherwise */
	@Override public boolean hasNext() {
		return size > 0;
	}

	/** Returns the next object in the iterator.
	 * @return next object
	 * @throws NoSuchElementException if there are no further element */
	@Override public E next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException("No such element");
		E element = head.getElement();
		head = head.getNext();
		size--;
		return element;
	}


	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Remove");
	}
}