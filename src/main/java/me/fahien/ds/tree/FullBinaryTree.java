package me.fahien.ds.tree;

import java.util.Iterator;

import me.fahien.ds.tree.binarytree.LinkedBinaryTree;
import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.exception.NonEmptyTreeException;
import me.fahien.ds.util.position.Position;

/** Full Binary Tree
 * @author Fahien */
public class FullBinaryTree<E> implements Tree<E> {

	private LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();

	public FullBinaryTree() {}

	@Override public int size() {
		return tree.size();
	}

	@Override public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override public Iterable<Position<E>> positions() {
		return tree.positions();
	}

	@Override public E replace(Position<E> position, E element) throws InvalidPositionException {
		return tree.replace(position, element);
	}

	@Override public Position<E> root() throws EmptyTreeException {
		return tree.root();
	}

	@Override public Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		return tree.parent(position);
	}

	@Override public Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException {
		return tree.children(position);
	}

	@Override public boolean isInternal(Position<E> position) throws InvalidPositionException {
		return tree.isInternal(position);
	}

	@Override public boolean isExternal(Position<E> position) throws InvalidPositionException {
		return tree.isExternal(position);
	}

	@Override public boolean isRoot(Position<E> position) throws InvalidPositionException {
		return tree.isRoot(position);
	}

	@Override public Iterator<E> iterator() {
		return tree.iterator();
	}

	/** Creates a node with this element and sets it root */
	public Position<E> addRoot(E element) throws NonEmptyTreeException {
		return tree.addRoot(element);
	}

	/** Creates a child, containing this element, of this position */
	public Position<E> addChild(E element, Position<E> position) {
		if (tree.hasLeft(position)) {
			position = tree.left(position);
			while (tree.hasRight(position)) {
				position = tree.right(position);
			}
			return tree.insertRight(element, position);
		} else {
			return tree.insertLeft(element, position);
		}
	}
}
