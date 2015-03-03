package me.fahien.ds.sequence;

import me.fahien.ds.arraylist.IndexList;
import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptySequenceException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.position.Position;

public interface Sequence<E> extends PositionList<E>, IndexList<E> {
	/** Bridge methods */
	public Position<E> atIndex (int index) throws BoundaryViolationException;
	public int indexOf (Position <E> position) throws InvalidPositionException;

	public E getFirst () throws EmptySequenceException;
	public E getLast () throws EmptySequenceException;
	public E removeFirst () throws EmptySequenceException;
	public E removeLast () throws EmptySequenceException;
}