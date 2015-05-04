package me.fahien.ds.deque;

import me.fahien.ds.exception.EmptyDequeException;
import me.fahien.ds.util.position.DLNode;

public class NodeDeque<E> implements Deque<E> {
	private final DLNode<E> header;
	private final DLNode<E> trailer;
	private int size;

	public NodeDeque() {
		header = new DLNode<>();
		trailer = new DLNode<>();
		header.setNext(trailer);
		trailer.setPrev(header);
	}

	@Override public int size() {
		return size;
	}

	@Override public boolean isEmpty() {
		return size == 0;
	}

	@Override public E getFirst() throws EmptyDequeException {
		if (isEmpty()) throw new EmptyDequeException();
		return header.getNext().getElement();
	}

	@Override public E getLast() throws EmptyDequeException {
		if (isEmpty()) throw new EmptyDequeException();
		return trailer.getPrev().getElement();
	}

	@Override public void addFirst(E element) {
		DLNode<E> node = new DLNode<>(header, header.getNext(), element);
		header.getNext().setPrev(node);
		header.setNext(node);
		size++;
	}

	@Override public void addLast(E element) {
		DLNode<E> node = new DLNode<>(trailer.getPrev(), trailer, element);
		trailer.getPrev().setNext(node);
		trailer.setPrev(node);
		size++;
	}

	@Override public E removeFirst() throws EmptyDequeException {
		if (isEmpty()) {
			throw new EmptyDequeException();
		}
		DLNode<E> node = header.getNext();
		header.setNext(node.getNext());
		node.getNext().setPrev(header);
		E element = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		size--;
		return element;
	}

	@Override public E removeLast() throws EmptyDequeException {
		if (isEmpty()) {
			throw new EmptyDequeException();
		}
		DLNode<E> node = trailer.getPrev();
		trailer.setPrev(node.getPrev());
		node.getPrev().setNext(trailer);
		E element = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		size--;
		return element;
	}

	@Override public String toString() {
		String string = "[";
		if (!isEmpty()) {
			DLNode<E> node = header.getNext();
			string += node.getElement();
			for (node = node.getNext(); node != trailer; node = node.getNext()) {
				string += ", " + node.getElement();
			}
		}
		string += "]";
		return string;
	}
}