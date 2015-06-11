package me.fahien.ds.tree.binarytree;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.tree.Tree;
import me.fahien.ds.util.position.Position;

/** Interface for a binary tree, in which each node has at most two children
 * @author Fahien */
public interface BinaryTree<E> extends Tree<E> {
	/** Returns the position of this position's left child */
	Position<E> left(Position<E> position) throws InvalidPositionException, BoundaryViolationException;

	/** Returns the position of this position's right child */
	Position<E> right(Position<E> position) throws InvalidPositionException, BoundaryViolationException;

	/** Tests whether this position has a left child */
	boolean hasLeft(Position<E> position) throws InvalidPositionException;

	/** Tests whether this position has a left child */
	boolean hasRight(Position<E> position) throws InvalidPositionException;
}