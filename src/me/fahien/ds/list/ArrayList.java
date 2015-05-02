package me.fahien.ds.list;

import me.fahien.ds.exception.IndexOutOfBoundsException;

public class ArrayList<E> implements List<E> {
	private static final int CAPACITY = 1024;

	private int capacity;
	private E[] array;
	private int size;

	public ArrayList() {
		this(CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		this.capacity = capacity;
		array = (E[]) new Object[capacity];
	}

	/** Checks whether the given index is in range [0, n - 1] */
	protected void checkIndex(int index, int n) throws IndexOutOfBoundsException {
		if (index < 0 || index >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + index);
	}

	protected void resize(int capacity) {
		this.capacity = capacity;
		@SuppressWarnings("unchecked")
		E temp[] = (E[]) new Object[capacity];
		System.arraycopy(array, 0, temp, 0, size());
		array = temp;	// Start using the new array
	}

	@Override public E remove(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		E element = array[index];
		array[index] = null;	// Help garbage collection
		System.arraycopy(array, index + 1, array, index, size() - 1 - index);
		size--;
		return element;
	}

	@Override public void add(int index, E element) throws IndexOutOfBoundsException {
		checkIndex(index, size() + 1);
		if (size() == array.length) {
			resize(capacity * 2);
		}
		System.arraycopy(array, index, array, index + 1, size() - index);
		array[index] = element;
		size++;
	}

	@Override public E set(int index, E element) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		E replaced = array[index];
		array[index] = element;
		return replaced;
	}

	@Override public E get(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		return array[index];
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public int size() {
		return size;
	}

	@Override public String toString() {
		String string = "[";
		if (!isEmpty()) {
			string += array[0];
			for (int i = 1; i < array.length && array[i] != null && i < size(); i++) {
				string += ", " + array[i];
			}
		}
		string += "]";
		return string;
	}
}