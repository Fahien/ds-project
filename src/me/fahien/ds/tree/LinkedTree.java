package me.fahien.ds.tree;

import java.util.Iterator;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.exception.NonEmptyTreeException;
import me.fahien.ds.exception.UndeletableNodeException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.position.Position;
import me.fahien.ds.util.position.TreeNode;
import me.fahien.ds.util.position.TreePosition;

public class LinkedTree<E> implements Tree<E> {
	protected TreePosition<E> root;
	protected int size;

	protected TreePosition<E> checkPosition(Position<E> position) throws InvalidPositionException{
		if (position == null)
			throw new InvalidPositionException("Position is null");
		try {
			return (TreePosition<E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid type of position");
		}
	}

	protected void preorderPositions(Position<E> position, PositionList<Position<E>> positionList) throws InvalidPositionException {
		positionList.addLast(position);
		if (isExternal(position))
			return;
		// Returns an iterator of position children
		for (Position<E> ePosition : childrenOf(position)) {
			preorderPositions(ePosition, positionList);
		}
	}

	@Override public Iterator<E> iterator() {
		Iterable<Position<E>> positions = getPositions();	// Iterable collection of nodes
		PositionList<E> positionList = new NodePositionList<E>();
		for (Position<E> position : positions) {
			positionList.addLast(position.getElement());
		}
		// Elements iterator
		return positionList.iterator();
	}

	@Override public int size() {
		return size;
	}

	@Override public boolean isEmpty() {
		return size == 0;
	}

	@Override public Iterable<Position<E>> getPositions() {
		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
		if (size != 0)
			preorderPositions(root, positions);
		return positions;
	}

	@Override public E replace(Position<E> position, E element) throws InvalidPositionException {
		TreePosition<E> node = checkPosition(position);
		E temp = node.getElement();
		node.setElement(element);
		return temp;
	}

	@Override public Position<E> getRoot() throws EmptyTreeException {
		if (size == 0 && root == null)
			throw new EmptyTreeException("The tree is empty");
		return root;
	}

	@Override public Position<E> parentOf(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		if (position == root)
			throw new BoundaryViolationException("The root has no parent");
		return checkPosition(position).getParent();
	}

	@Override public Iterable<Position<E>> childrenOf(Position<E> position) throws InvalidPositionException {
		PositionList<Position<E>> positionList = new NodePositionList<Position<E>>();
		if (size != 0)
			preorderPositions(position, positionList);
		return positionList;
	}

	@Override public boolean isInternal(Position<E> position) throws InvalidPositionException {
		TreePosition<E> node = checkPosition(position);
		return node != root && !node.getChildren().isEmpty();
	}

	@Override public boolean isExternal(Position<E> position) throws InvalidPositionException {
		TreePosition<E> node = checkPosition(position);
		return node != root && node.getChildren().isEmpty();
	}

	@Override public boolean isRoot(Position<E> position) throws InvalidPositionException {
		return checkPosition(position) == root;
	}

	public E removeRoot() throws EmptyTreeException, UndeletableNodeException {
		if (size == 0)
			throw new EmptyTreeException("The tree is empty");
		else if (size > 1)
			throw new UndeletableNodeException("The root is undeletable");
		else {
			E element = root.getElement();
			root = null;
			return element;
		}
	}

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
		return element;
	}

	public E remove(Position<E> position) throws InvalidPositionException {
		if(size == 0)
			throw new InvalidPositionException("The tree is empty");
		TreePosition<E> node = checkPosition(position);
		if (!isExternal(node))
			throw new InvalidPositionException("The tree is empty");
		TreePosition<E> parent = node.getParent();
		PositionList<Position<E>> children = parent.getChildren();
		Iterator<Position<E>> iter = children.iterator();
		while (iter.hasNext()) {
			Position<E> childPosition = iter.next();
			if (childPosition.getElement().equals(node))
				iter.remove();
		}
		parent.setChildren(children);
		return node.getElement();
	}

	public Position<E> addRoot(E element)  throws NonEmptyTreeException {
		if (size != 0 && root != null)
			throw new NonEmptyTreeException("The tree is not empty");
		root = new TreeNode<E>(element, null, null);
		return root;
	}

	public Position<E> addChild(E element, Position<E> position) {
		TreePosition<E> parent = checkPosition(position);
		TreePosition<E> child = new TreeNode<E>(element, parent, null);
		PositionList<Position<E>> children = parent.getChildren();
		children.addLast(child);
		parent.setChildren(children);
		return child;
	}
}