package me.fahien.ds.tree;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.util.position.Position;

public interface Tree<E> extends Iterable<E> {
	int size();
	boolean isEmpty();
	Iterable<Position<E>> getPositions();
	E replace(Position<E> position, E element) throws InvalidPositionException;
	Position<E> getRoot() throws EmptyTreeException;
	Position<E> parentOf(Position<E> position) throws InvalidPositionException, BoundaryViolationException;
	Iterable<Position<E>> childrenOf(Position<E> position) throws InvalidPositionException;
	boolean isInternal(Position<E> position) throws InvalidPositionException;
	boolean isExternal(Position<E> position) throws InvalidPositionException;
	boolean isRoot(Position<E> position) throws InvalidPositionException;
}