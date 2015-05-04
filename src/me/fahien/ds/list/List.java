package me.fahien.ds.list;

import me.fahien.ds.exception.IndexOutOfBoundsException;

public interface List<E> {
	/** Removes and returns the element at that index,
	 * shifting subsequent elements earlier */
	E remove(int index) throws IndexOutOfBoundsException;

	/** Inserts that element to be at that index,
	 * shifting all subsequent element later */
	void add(int index, E element) throws IndexOutOfBoundsException;

	/** Replaces the element at that index with another element,
	 * and returns the replaced element */
	E set(int index, E element) throws IndexOutOfBoundsException;

	/** Returns, but does not remove, the element at that index */
	E get(int index) throws IndexOutOfBoundsException;

	/** Returns whether the list is empty */
	boolean isEmpty();

	/** Returns the number of elements in this list */
	int size();
}