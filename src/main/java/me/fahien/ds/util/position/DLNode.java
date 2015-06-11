package me.fahien.ds.util.position;

import me.fahien.ds.exception.InvalidPositionException;

/** Double-Linked Node
 * @author Fahien */
public class DLNode<E> implements Position<E> {
	private E element;
	private DLNode<E> next;
	private DLNode<E> prev;

	public DLNode() {}

	public DLNode(DLNode<E> prev, DLNode<E> next, E element) {
		this.prev = prev;
		this.next = next;
		this.element = element;
	}

	@Override public E getElement() throws InvalidPositionException {
		if (prev == null || next == null)
			throw new InvalidPositionException("Invalid position for " + element);
		return element;
	}

	/** @param element The element*/
	public void setElement(E element) {
		this.element = element;
	}

	/** Returns next DLNode */
	public DLNode<E> getNext() {
		return next;
	}

	/** Returns previous DLNode */
	public DLNode<E> getPrev() {
		return prev;
	}

	/** @param next Next DLNode */
	public void setNext(DLNode<E> next) {
		this.next = next;
	}

	/** @param prev Previous DLNode */
	public void setPrev(DLNode<E> prev) {
		this.prev = prev;
	}
}