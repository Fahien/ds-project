package me.fahien.ds.sequence;

import me.fahien.ds.exception.EmptySequenceException;
import me.fahien.ds.exception.IndexOutOfBoundsException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.list.List;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.position.Position;

public interface Sequence<E> extends PositionList<E>, List<E> {
	/** Returns the position of the entry at this index */
	Position<E> atIndex(int index) throws IndexOutOfBoundsException;

	/** Returns the index of the entry at this position */
	int indexOf(Position <E> position) throws InvalidPositionException;

	/** Returns the first element */
	E getFirst() throws EmptySequenceException;

	/** Returns the last element */
	E getLast() throws EmptySequenceException;

	/** Removes the first element and returns it */
	E removeFirst() throws EmptySequenceException;

	/** Removes the last element and returns it */
	E removeLast() throws EmptySequenceException;
}