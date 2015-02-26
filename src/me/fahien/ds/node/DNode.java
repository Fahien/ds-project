package me.fahien.ds.node;

import me.fahien.ds.exception.InvalidPositionException;

public class DNode<E> implements Position<E> {
	private E element;
	private DNode<E> next, prev;

	public DNode () {
		this(null, null, null);
	}

	public DNode (DNode<E> prev, DNode<E> next, E element) {
		this.prev = prev;
		this.next = next;
		this.element = element;
	}

	@Override
	public E getElement () {
		if ((prev == null) || (next == null))
			throw new InvalidPositionException("Invalid position");
		return element;
	}

	/** @return next DNode */
	public DNode<E> getNext () {
		return next;
	}

	/** @return previous DNode */
	public DNode<E> getPrev () {
		return prev;
	}

	/** @param element */
	public void setElement (E element) {
		this.element = element;
	}

	/** @param next */
	public void setNext (DNode<E> next) {
		this.next = next;
	}

	/** @param prev */
	public void setPrev (DNode<E> prev) {
		this.prev = prev;
	}
}