package me.fahien.ds.stack;

import me.fahien.ds.deque.Deque;
import me.fahien.ds.deque.NodeDeque;
import me.fahien.ds.exception.EmptyDequeException;
import me.fahien.ds.exception.EmptyStackException;

/** Example of the Adapter pattern.
 * This is an implementation of a {@link Stack}
 * using a {@link me.fahien.ds.deque.Deque} as storage
 * @author Fahien */
public class DequeStack<E> implements Stack<E> {
	private Deque<E> deque;

	public DequeStack() {
		deque = new NodeDeque<>();
	}

	@Override public int size() { return deque.size(); }

	@Override public boolean isEmpty() { return deque.isEmpty(); }

	@Override public void push(E element) { deque.addLast(element); }

	@Override public E top() throws EmptyStackException {
		try {
			return deque.getLast();
		} catch (EmptyDequeException e) {
			throw new EmptyStackException("The stack is empty");
		}
	}

	@Override public E pop() throws EmptyStackException {
		try {
			return deque.removeLast();
		} catch (EmptyDequeException e) {
			throw new EmptyStackException("The stack is empty");
		}
	}
}
