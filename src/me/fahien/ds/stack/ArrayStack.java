package me.fahien.ds.stack;

import me.fahien.ds.exception.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {
	/** Default array capacity */
	private static final int CAPACITY = 1024;

	private int capacity;
	/** Generic array used for storage */
	private E data[];
	/** Index of the top element in the stack */
	private int top = -1;

	/** Constructs stack with default capacity */
	public ArrayStack() {
		this(CAPACITY);
	}

	/** Construct stack with given capacity */
	@SuppressWarnings("unchecked") public ArrayStack(int capacity) {
		this.capacity = capacity;
		data = (E[]) new Object[capacity];
	}

	@Override public int size() {
		return top + 1;
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Empty stack");
		return data[top];
	}

	@Override public void push(E element) {
		if (size() == data.length) {
			capacity *= 2;
			@SuppressWarnings("unchecked")
			E temp[] = (E[]) new Object[capacity];
			System.arraycopy(data, 0, temp, 0, data.length);
			data = temp;
		}
		data[++top] = element;
	}

	@Override public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Empty stack");
		E element = data[top];
		data[top--] = null;
		return element;
	}

	@Override public String toString() {
		String string = "[";
		if (!isEmpty()) {
			string += data[0];
			for (int i = 1; i < data.length && data[i] != null && i < size(); i++) {
				string += ", " + data[i];
			}
		}
		string += "]";
		return string;
	}

	public void union(Stack<E> stack) {
		Stack<E> temp = new ArrayStack<>();
		while (!isEmpty()) temp.push(pop());
		int stackSize = stack.size();
		while (!stack.isEmpty()) temp.push(stack.pop());
		E element;
		for (int i = 0; i < stackSize; i++) {
			element = temp.pop();
			stack.push(element);
			push(element);
		}
		while (!temp.isEmpty()) push(temp.pop());
	}
}