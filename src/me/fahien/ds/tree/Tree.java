package me.fahien.ds.tree;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.util.position.Position;

public interface Tree<E> extends Iterable<E> {
	public int size ();
	public boolean isEmpty ();
	public Iterable<Position<E>> getPositions ();
	public E replace (Position<E> position, E element) throws InvalidPositionException;
	public Position<E> getRoot () throws EmptyTreeException;
	public Position<E> parentOf (Position<E> position) throws InvalidPositionException, BoundaryViolationException;
	public Iterable<Position<E>> childrenOf (Position<E> position) throws InvalidPositionException;
	public boolean isInternal (Position<E> position) throws InvalidPositionException;
	public boolean isExternal (Position<E> position) throws InvalidPositionException;
	public boolean isRoot (Position<E> position) throws InvalidPositionException;
}