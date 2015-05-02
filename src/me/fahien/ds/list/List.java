package me.fahien.ds.list;

import me.fahien.ds.exception.IndexOutOfBoundsException;

public interface List<E> {
	/** Removes and returns the element at that index,
	 * shifting subsequent elements earlier */
	public E remove(int index) throws IndexOutOfBoundsException;
	/** Inserts that element to be at that index,
	 * shifting all subsequent element later */
	public void add(int index, E element) throws IndexOutOfBoundsException;
	/** Replaces the element at that index with another element,
	 * and returns the replaced element */
	public E set(int index, E element) throws IndexOutOfBoundsException;
	/** Returns, but does not remove, the element at that index */
	public E get(int index) throws IndexOutOfBoundsException;
	/** Returns whether the list is empty */
	public boolean isEmpty();
	/** Returns the number of elements in this list */
	public int size();
}