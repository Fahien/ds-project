package me.fahien.ds.queue;

import me.fahien.ds.exception.EmptyQueueException;
import me.fahien.ds.util.position.Node;

public class NodeQueue<E> implements Queue<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;

	@Override public int size() {
		return size;
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("The queue is empty");
		return head.getElement();
	}

	@Override public void enqueue(E element) {
		if (isEmpty()) {
			tail = new Node<E>(element, null);
			head = new Node<E>(element, tail);
		}
		else if (size() == 1) {
			tail.setElement(element);
		}
		else {
			Node<E> next = new Node<>(element, null);
			tail.setNext(next);
			tail = next;
		}
		size++;
	}

	@Override public E dequeue() throws EmptyQueueException {
		if(size() == 0)
			throw new EmptyQueueException("The queue is empty");
		E temp = head.getElement();
		head = head.getNext();
		size--;
		if (size == 0) {
			tail = null;
		}
		return temp;
	}

	@Override public String toString() {
		String string = "[";
		if (!isEmpty()) {
			string += head.getElement();
			Node<E> temp = head.getNext();
			if (size() > 1) {
				while (temp != tail) {
					string += ", " + temp.getElement();
					temp = temp.getNext();
				}
				string += temp.getElement();
			}
		}
		string += "]";
		return string;
	}
}
