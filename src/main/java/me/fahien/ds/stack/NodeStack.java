package me.fahien.ds.stack;

import me.fahien.ds.exception.EmptyStackException;
import me.fahien.ds.util.position.Node;

public class NodeStack<E> implements Stack<E> {
	protected Node<E> top;
	protected int size;

	public NodeStack() {
		top = null;
		size = 0;
	}

	@Override public int size() {
		return size;
	}

	@Override public boolean isEmpty() {
		return top == null && size == 0;
	}

	@Override public E top() throws EmptyStackException {
		if (isEmpty()) throw new EmptyStackException("The stack is empty");
		return top.getElement();
	}

	@Override public void push(E element) {
		top = new Node<>(element, top);
		size++;
	}

	@Override public E pop() throws EmptyStackException {
		if (isEmpty()) throw new EmptyStackException("The stack is empty");
		E temp = top.getElement();
		top = top.getNext();
		size--;
		return temp;
	}

	@Override public String toString() {
		String string = "[";
		if (!isEmpty()) {
			string += top.getElement();
			for (Node<E> node = top.getNext(); node != null; node = node.getNext()) {
				string += ", " + node.getElement();
			}
		}
		string += "]";
		return string;
	}
}