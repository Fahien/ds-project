package me.fahien.ds.node;

public class DLNode<E> {
	private E element;
	private DLNode<E> prev, next;

	public DLNode () {
		this(null, null, null);
	}

	/**
	 * @param element	The element
	 * @param prev	Previous DLNode
	 * @param next	Next DLNode
	 */
	public DLNode (E element, DLNode<E> prev, DLNode<E> next) {
		this.element = element;
		this.prev = prev;
		this.next = next;
	}

	public E getElement () {
		return element;
	}
	
	public void setElement (E element) {
		this.element = element;
	}

	public DLNode<E> getPrev () {
		return prev;
	}

	public void setPrev (DLNode<E> prev) {
		this.prev = prev;
	}

	public DLNode<E> getNext () {
		return next;
	}

	public void setNext (DLNode<E> next) {
		this.next = next;
	}
}