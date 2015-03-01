package me.fahien.ds.arraylist;

import me.fahien.ds.exception.IndexOutOfBoundsException;

public interface IndexList<E> {
	public E remove (int index) throws IndexOutOfBoundsException;
	public void add (int index, E element) throws IndexOutOfBoundsException;
	public E set (int index, E element) throws IndexOutOfBoundsException;
	public E get (int index) throws IndexOutOfBoundsException;
	public boolean isEmpty ();
	public int size ();
}