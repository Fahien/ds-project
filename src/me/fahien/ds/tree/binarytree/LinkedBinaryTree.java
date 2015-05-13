package me.fahien.ds.tree.binarytree;

import java.util.Iterator;
import java.util.logging.Logger;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyListException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.exception.NonEmptyTreeException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.position.BTNode;
import me.fahien.ds.util.position.BTPosition;
import me.fahien.ds.util.position.Position;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
	private static Logger logger = Logger.getLogger(LinkedBinaryTree.class.getName());
	private int size = 0;
	private BTPosition<E> root;

	public LinkedBinaryTree() {}

	protected BTPosition<E> checkPosition(Position<E> position) throws InvalidPositionException{
		if (position == null)
			throw new InvalidPositionException("The position is null");
		try {
			return (BTPosition<E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid type of position");
		}
	}

	protected void preorderPositions(Position<E> position, PositionList<Position<E>> positionList) throws InvalidPositionException {
		positionList.addLast(position);
		if (hasLeft(position)) {
			preorderPositions(left(position), positionList);
		}
		if (hasRight(position)) {
			preorderPositions(right(position), positionList);
		}
	}

	@Override public int size() {
		return size;
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public Iterable<Position<E>> positions() {
		PositionList<Position<E>> positions = new NodePositionList<>();
		if (size() > 0)
			try {
				preorderPositions(root(), positions);
			} catch (InvalidPositionException | EmptyTreeException e) {
				logger.warning(e.getMessage());
			}
		return positions;
	}

	@Override public E replace(Position<E> position, E element) throws InvalidPositionException {
		BTPosition<E> node = checkPosition(position);
		E temp = node.getElement();
		node.setElement(element);
		return temp;
	}

	@Override public Position<E> root() throws EmptyTreeException {
		if (size() == 0)
			throw new EmptyTreeException("The binary tree is empty");
		return root;
	}

	@Override public Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		if (position == root)
			throw new BoundaryViolationException("The root has no parent");
		return checkPosition(position).getParent();
	}

	@Override public Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException {
		PositionList<Position<E>> positionList = new NodePositionList<>();
		if (size() > 0)
			preorderPositions(position, positionList);
		return positionList;
	}

	@Override public boolean isInternal(Position<E> position) throws InvalidPositionException {
		return hasLeft(position) && hasRight(position);
	}

	@Override public boolean isExternal(Position<E> position) throws InvalidPositionException {
		return !hasLeft(position) && !hasRight(position);
	}

	@Override public boolean isRoot(Position<E> position) throws InvalidPositionException {
		return checkPosition(position) == root;
	}

	@Override public Iterator<E> iterator() {
		PositionList<E> elements = new NodePositionList<>();
		for (Position<E> position : positions())
			elements.addLast(position.getElement());
		return elements.iterator();
	}

	@Override public Position<E> left(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> node = checkPosition(position);
		if (!hasLeft(node))
			throw new BoundaryViolationException("This position has no left child");
		return node.getLeft();
	}

	@Override public Position<E> right(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> node = checkPosition(position);
		if (!hasRight(node))
			throw new BoundaryViolationException("This position has no right child");
		return node.getRight();
	}

	@Override public boolean hasLeft(Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getLeft() != null;
	}

	@Override public boolean hasRight(Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getRight() != null;
	}

	/** Creates a new root with this element */
	public Position<E> addRoot(E element) throws NonEmptyTreeException {
		if (size() > 0)
			throw new NonEmptyTreeException("The binary tree is not empty");
		root = new BTNode<>(element, null, null, null);
		size++;
		return root;
	}

	/** Creates a left child for this position */
	public Position<E> insertLeft(E element, Position<E> position) throws InvalidPositionException {
		BTPosition<E> parent = checkPosition(position);
		if (hasLeft(parent))
			throw new InvalidPositionException("This position have already a left child");
		BTPosition<E> left = new BTNode<>(element, parent, null, null);
		parent.setLeft(left);
		size++;
		return left;
	}

	/** Creates a right child for this position */
	public Position<E> insertRight(E element, Position<E> position) throws InvalidPositionException {
		BTPosition<E> parent = checkPosition(position);
		if (hasRight(parent))
			throw new InvalidPositionException("This position have already a right child");
		BTPosition<E> right = new BTNode<>(element, parent, null, null);
		parent.setRight(right);
		size++;
		return right;
	}

	/** Attaches children to the leafs of the tree */
	public void attachLeaves(PositionList<E> list) {
		for (Position<E> position : positions()) {
			if (!hasLeft(position) && !hasRight(position)) {
				try {
					insertLeft(list.remove(list.first()), position);
					insertRight(list.remove(list.first()), position);
				} catch (EmptyListException e) {
					logger.warning("The list has not enough elements");
				}
			}
		}
	}
}