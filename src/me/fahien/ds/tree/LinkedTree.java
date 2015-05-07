package me.fahien.ds.tree;

import java.util.Iterator;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.exception.NonEmptyTreeException;
import me.fahien.ds.exception.UndeletableNodeException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.position.Position;
import me.fahien.ds.util.position.TreeNode;
import me.fahien.ds.util.position.TreePosition;

public class LinkedTree<E> extends AbstractTree<E> {
	private TreePosition<E> root;
	private int size;

	/** Validates the position and returns it as a TreePosition */
	protected TreePosition<E> checkPosition(Position<E> position) throws InvalidPositionException{
		if (position == null)
			throw new InvalidPositionException("Position is null");
		try {
			return (TreePosition<E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid position type");
		}
	}

	protected void preorderPositions(Position<E> position, PositionList<Position<E>> list) throws InvalidPositionException {
		list.addLast(position);
		if (!isExternal(position)) {
			for (Position<E> child : children(position)) {
				preorderPositions(child, list);
			}
		}
	}

	@Override public Iterator<E> iterator() {
		Iterable<Position<E>> positions = positions();	// Iterable collection of nodes
		PositionList<E> list = new NodePositionList<>();
		for (Position<E> position : positions) {
			list.addLast(position.getElement());
		}
		// Elements iterator
		return list.iterator();
	}

	@Override public int size() {
		return size;
	}

	@Override public Iterable<Position<E>> positions() {
		PositionList<Position<E>> positions = new NodePositionList<>();
		if (!isEmpty())
			preorderPositions(root, positions);
		return positions;
	}

	@Override public E replace(Position<E> position, E element) throws InvalidPositionException {
		TreePosition<E> node = checkPosition(position);
		E temp = node.getElement();
		node.setElement(element);
		return temp;
	}

	@Override public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException("The tree is empty");
		return root;
	}

	@Override public Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		if (position == root)
			throw new BoundaryViolationException("The root has no parent");
		return checkPosition(position).getParent();
	}

	@Override public Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException {
		PositionList<Position<E>> list = new NodePositionList<>();
		if (!isEmpty())
			preorderPositions(position, list);
		return list;
	}

	@Override public boolean isInternal(Position<E> position) throws InvalidPositionException {
		TreePosition<E> node = checkPosition(position);
		return !node.getChildren().isEmpty();
	}

	@Override public boolean isExternal(Position<E> position) throws InvalidPositionException {
		TreePosition<E> node = checkPosition(position);
		return node.getChildren().isEmpty();
	}

	@Override public boolean isRoot(Position<E> position) throws InvalidPositionException {
		return checkPosition(position) == root;
	}

	/** Removes the root position and returns its element */
	public E removeRoot() throws EmptyTreeException, UndeletableNodeException {
		if (isEmpty())
			throw new EmptyTreeException("The tree is empty");
		else if (size() > 1)
			throw new UndeletableNodeException("The root is undeletable");
		else {
			E element = root.getElement();
			root = null;
			size--;
			return element;
		}
	}

	/** If the first child of this position is a leaf,
	 * this method will remove it and return its element */
	public E removeExternalChild(Position<E> position) throws InvalidPositionException, UndeletableNodeException {
		TreePosition<E> parent = checkPosition(position);
		if (isExternal(parent))
			throw new InvalidPositionException("The node has no children");
		PositionList<Position<E>> children = parent.getChildren();
		Position<E> childPosition = children.first().getElement();
		if (!isExternal(childPosition))
			throw new UndeletableNodeException("First child is not external");
		E element = childPosition.getElement();
		children.remove(children.first());
		parent.setChildren(children);
		size--;
		return element;
	}

	/** If this position is a leaf, this method will remove it and returns its element */
	public E remove(Position<E> position) throws InvalidPositionException, EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException("The tree is empty");
		TreePosition<E> node = checkPosition(position);
		if (!isExternal(node))
			throw new InvalidPositionException("Cannot delete an internal node");
		if (isRoot(node)) {
			return removeRoot();
		}
		TreePosition<E> parent = node.getParent();
		PositionList<Position<E>> children = parent.getChildren();
		Iterator<Position<E>> iter = children.iterator();
		while (iter.hasNext()) {
			Position<E> childPosition = iter.next();
			if (childPosition.equals(node))
				iter.remove();
		}
		parent.setChildren(children);
		size--;
		return node.getElement();
	}

	/** Creates a node with this element and sets it root */
	public Position<E> addRoot(E element) throws NonEmptyTreeException {
		if (!isEmpty())
			throw new NonEmptyTreeException("The tree is not empty");
		root = new TreeNode<>(element, null);
		size++;
		return root;
	}

	/** Creates a child, containing this element, of this position */
	public Position<E> addChild(E element, Position<E> position) {
		TreePosition<E> parent = checkPosition(position);
		TreePosition<E> child = new TreeNode<>(element, parent);
		PositionList<Position<E>> children = parent.getChildren();
		children.addLast(child);
		parent.setChildren(children);
		size++;
		return child;
	}
}