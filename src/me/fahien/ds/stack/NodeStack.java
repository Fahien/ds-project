package me.fahien.ds.stack;

import me.fahien.ds.exception.EmptyStackException;

public class NodeStack<E> implements Stack<E> {
	protected Node<E> top;
	protected int size;

	public NodeStack () {
		top = null;
		size = 0;
	}

	@Override
	public int size () {
		return size;
	}

	@Override
	public boolean isEmpty () {
		return top == null && size == 0;
	}

	@Override
	public E top () throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("The stack is empty");
		return top.getElement();
	}

	@Override
	public void push (E element) {
		Node<E> node = new Node<E>(element, top);
		top = node;
		size++;
	}

	@Override
	public E pop () throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("The stack is empty");
		E temp = top.getElement();
		top = top.getNext();
		size--;
		return temp;
	}

	@Override
	public String toString () {
		String string = "[";
		if (size > 0 && top != null) {
			string += top.getElement().toString();
			for (Node<E> node = top.getNext(); node != null; node = node.getNext()) {
				string += ", " + node.getElement().toString();
			}
		}
		return string += "]";
	}
}