package me.fahien.ds.arraylist;

import me.fahien.ds.exception.IndexOutOfBoundsException;

public class ArrayList<E> implements IndexList<E> {
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

	@Override public E remove(int index) throws IndexOutOfBoundsException {
		if (index >= size())
			throw new IndexOutOfBoundsException("The index is out of bounds");
		E element = array[index];
		array[index] = null;
		System.arraycopy(array, index + 1, array, index, size() - 1 - index);
		size--;
		return element;
	}

	@Override public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index > size())
			throw new IndexOutOfBoundsException("The index is out of bounds");
		if (size() == array.length) {
			capacity *= 2;
			@SuppressWarnings("unchecked")
			E temp[] = (E[]) new Object[capacity];
			System.arraycopy(array, 0, temp, 0, array.length);
			array = temp;
		}
		System.arraycopy(array, index, array, index + 1, size() - index);
		array[index] = element;
		size++;
	}

	@Override public E set(int index, E element) throws IndexOutOfBoundsException {
		if (index >= size())
			throw new IndexOutOfBoundsException("The index is out of bounds");
		E replace = array[index];
		array[index] = element;
		return replace;
	}

	@Override public E get(int index) throws IndexOutOfBoundsException {
		if (index >= size())
			throw new IndexOutOfBoundsException("The index is out of bounds");
		return array[index];
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public int size() {
		return size;
	}
}