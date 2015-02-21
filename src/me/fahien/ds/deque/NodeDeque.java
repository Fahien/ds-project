package me.fahien.ds.deque;

import me.fahien.ds.exception.EmptyDequeException;
import me.fahien.ds.node.DLNode;

public class NodeDeque<E> implements Deque<E> {
	private DLNode<E> header, trailer;
	private int size;

	public NodeDeque () {
		header = new DLNode<E>();
		trailer = new DLNode<E>();
		header.setNext(trailer);
		trailer.setPrev(header);
	}

	@Override
	public int size () {
		return size;
	}

	@Override
	public boolean isEmpty () {
		return size == 0;
	}

	@Override
	public E getFirst () throws EmptyDequeException {
		return header.getNext().getElement();
	}

	@Override
	public E getLast () throws EmptyDequeException {
		return trailer.getPrev().getElement();
	}

	@Override
	public void addFirst (E element) {
		DLNode<E> node = new DLNode<E>(element, header, header.getNext());
		header.getNext().setPrev(node);
		header.setNext(node);
		size++;
	}

	@Override
	public void addLast (E element) {
		DLNode<E> node = new DLNode<E>(element, trailer, trailer.getPrev());
		trailer.getPrev().setNext(node);
		trailer.setPrev(node);
		size++;
	}

	@Override
	public E removeFirst () throws EmptyDequeException {
		if (isEmpty()) {
			throw new EmptyDequeException("The deque is empty");
		}
		DLNode<E> node = header.getNext();
		header.setNext(node.getNext());
		node.getNext().setPrev(header);
		node.setNext(null);
		node.setPrev(null);
		size--;
		return node.getElement();
	}

	@Override
	public E removeLast () throws EmptyDequeException {
		if (isEmpty()) {
			throw new EmptyDequeException("The deque is empty");
		}
		DLNode<E> node = trailer.getPrev();
		trailer.setPrev(node.getPrev());
		node.getPrev().setNext(trailer);
		node.setNext(null);
		node.setPrev(null);
		size--;
		return node.getElement();
	}

	@Override
	public String toString () {
		String string = "[";
		if (!isEmpty()) {
			DLNode<E> node = header.getNext();
			string += node.getElement();
			for (node = node.getNext(); node.getElement() != null; node = node.getNext()) {
				string += ", " + node.getElement();
			}
		}
		return string += "]";
	}
}