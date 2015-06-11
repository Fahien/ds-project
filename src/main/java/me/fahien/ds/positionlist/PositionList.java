package me.fahien.ds.positionlist;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyListException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.util.position.Position;

public interface PositionList<E> extends Iterable<E> {
	/** Returns the number of elements in the list */
	int size();

	/** Tests whether the list is empty */
	boolean isEmpty();

	/** Inserts an element immediately before this position and returns its new Position */
	Position<E> addBefore(Position<E> position, E element) throws InvalidPositionException;

	/** Inserts an element immediately after this position and returns its new Position */
	Position<E> addAfter(Position<E> position, E element) throws InvalidPositionException;

	/** Inserts an element at the front of the list */
	void addFirst(E element);

	/** Inserts an element at the back of the list */
	void addLast(E element);

	/** Removes the element stored at this position and returns it */
	E remove(Position<E> position) throws InvalidPositionException;

	/** Replaces the element stored at this position and returns the replaced element */
	E set(Position<E> position, E element) throws InvalidPositionException;

	/** Returns the first Position in the list
	 * @throws EmptyListException if the list is empty */
	Position<E> first() throws EmptyListException;

	/** Returns the last Position in the list
	 * @throws EmptyListException if the list is empty */
	Position<E> last() throws EmptyListException;

	/** Returns the Position immediately before this position */
	Position<E> prev(Position<E> position) throws InvalidPositionException, BoundaryViolationException;

	/** Returns the Position immediately after this position */
	Position<E> next(Position<E> position) throws InvalidPositionException, BoundaryViolationException;

	/** Returns an iterable representation of the list's positions. */
	Iterable<Position<E>> positions();
}