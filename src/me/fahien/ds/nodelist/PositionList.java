package me.fahien.ds.nodelist;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyListException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.position.Position;

public interface PositionList<E> {
	/** Generic methods */
	public int size ();
	public boolean isEmpty ();
	/** Update methods */
	public Position<E> addBefore (Position<E> position, E element) throws InvalidPositionException;
	public Position<E> addAfter (Position<E> position, E element) throws InvalidPositionException;
	public void addFirst (E element);
	public void addLast (E element);
	public E remove (Position<E> position) throws InvalidPositionException;
	public E set (Position<E> position, E element) throws InvalidPositionException;
	/** Access methods */
	public Position<E> first () throws EmptyListException;
	public Position<E> last () throws EmptyListException;
	public Position<E> prev (Position<E> position) throws InvalidPositionException, BoundaryViolationException;
	public Position<E> next (Position<E> position) throws InvalidPositionException, BoundaryViolationException;
}