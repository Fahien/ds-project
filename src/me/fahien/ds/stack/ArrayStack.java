package me.fahien.ds.stack;

import me.fahien.ds.exception.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000;

	private int capacity;
	private E stack[];
	private int top = -1;

	public ArrayStack () {
		this(CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayStack (int capacity) {
		this.capacity = capacity;
		stack = (E[]) new Object[capacity];
	}

	@Override
	public int size () {
		return top + 1;
	}

	@Override
	public boolean isEmpty () {
		return size() == 0;
	}

	@Override
	public E top () throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("The stack is empty");
		return stack[top];
	}

	@Override
	public void push (E element) {
		if (size() == stack.length) {
			@SuppressWarnings("unchecked")
			E temp[] = (E[]) new Object[capacity * 2];
			for (int i = 0; i < stack.length; i++) {
				temp[i] = stack[i];
			}
			stack = temp;
		}
		stack[++top] = element;
	}

	@Override
	public E pop () throws EmptyStackException {
		E element;
		if (isEmpty())
			throw new EmptyStackException("The stack is empty");
		element = stack[top];
		stack[top--] = null;
		return element;
	}

	@Override
	public String toString () {
		String string = "[";
		if (stack.length > 0 && size() > 0) {
			string += stack[0].toString();
			for (int i = 1; i < stack.length && stack[i] != null && i < size(); i++) {
				string += ", " + stack[i].toString();
			}
		}
		return string += "]";
	}
}