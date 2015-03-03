package me.fahien.ds.queue;

import me.fahien.ds.exception.EmptyQueueException;
import me.fahien.ds.util.position.Node;

public class NodeQueue<E> implements Queue<E> {
	protected Node<E> head, tail;
	protected int size;

	@Override
	public int size () {
		return size;
	}

	@Override
	public boolean isEmpty () {
		return size == 0;
	}

	@Override
	public E front () throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("The queue is empty");
		return head.getElement();
	}

	@Override
	public void enqueue (E element) {
		Node<E> node = new Node<E>(element, null);
		tail.setNext(node);
		tail = node;
		size++;
	}

	@Override
	public E dequeue () throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("The queue is empty");
		E temp = head.getElement();
		head = head.getNext();
		if (--size == 0)
			tail = null;
		return temp;
	}

	@Override
	public String toString () {
		String string = "[";
		if (!isEmpty()) {
			string += head.getElement();
			for (Node<E> node = head.getNext(); node != null; node = node.getNext()) {
				string += ", " + node.getElement();
			}
		}
		return string += "]";
	}
}