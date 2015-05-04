package me.fahien.ds.binarytree;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.tree.Tree;
import me.fahien.ds.util.position.Position;

public interface BinaryTree<E> extends Tree<E> {
	Position<E> getLeft(Position<E> position) throws InvalidPositionException, BoundaryViolationException;
	Position<E> getRight(Position<E> position) throws InvalidPositionException, BoundaryViolationException;
	boolean hasLeft(Position<E> position) throws InvalidPositionException;
	boolean hasRight(Position<E> position) throws InvalidPositionException;
}