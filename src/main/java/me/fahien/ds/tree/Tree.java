package me.fahien.ds.tree;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.util.position.Position;

/** An interface for a tree where nodes can have an arbitrary number of children
 * @author Fahien */
public interface Tree<E> extends Iterable<E> {
	/** Returns the number of positions that are contained in the tree */
	int size();

	/** Returns true if the tree does not contain any positions */
	boolean isEmpty();

	/** Returns an iterable collection of all positions of the tree */
	Iterable<Position<E>> positions();

	/** Replaces and returns the element of this position */
	E replace(Position<E> position, E element) throws InvalidPositionException;

	/**  Returns the position of the root of the tree
	 * @throws EmptyTreeException if empty */
	Position<E> root() throws EmptyTreeException;

	/** Returns the position of the parent of this position */
	Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException;

	/** returns an iterable collection containing the children of this position */
	Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException;

	/** Returns true if this position has at least one child */
	boolean isInternal(Position<E> position) throws InvalidPositionException;

	/** Returns true if this position does not have any children */
	boolean isExternal(Position<E> position) throws InvalidPositionException;

	/** Returns true if this position is the root of the tree */
	boolean isRoot(Position<E> position) throws InvalidPositionException;
}