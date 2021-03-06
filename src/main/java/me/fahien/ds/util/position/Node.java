package me.fahien.ds.util.position;

public class Node<E> implements Position<E> {
	private E element;
	private Node<E> next;

	public Node(E element, Node <E> next) {
		this.element = element;
		this.next = next;
	}

	@Override public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}
}