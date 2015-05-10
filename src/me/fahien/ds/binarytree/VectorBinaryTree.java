package me.fahien.ds.binarytree;

import java.util.Iterator;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.exception.NonEmptyTreeException;
import me.fahien.ds.list.ArrayList;
import me.fahien.ds.list.List;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.position.BTNode;
import me.fahien.ds.util.position.BTPosition;
import me.fahien.ds.util.position.Position;

/** Implementation of a BinaryTree using a vector
 * @author Fahien */
public class VectorBinaryTree<E> implements BinaryTree<E> {
	private List<Position<E>> vector = new ArrayList<>();
	private int size = 0;

	public VectorBinaryTree() {
		vector.add(0, null);
	}

	protected BTPosition<E> checkPosition(Position<E> position) throws InvalidPositionException{
		if (position == null)
			throw new InvalidPositionException("The position is null");
		try {
			return (BTPosition<E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid type of position");
		}
	}

	@Override public Position<E> left(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		position = checkPosition(position);
		for (int i = 0; i < size(); i++) {
			if (position.equals(vector.get(i))) {
				Position<E> child = vector.get(2*i);
				if (child != null) {
					return child;
				}
				break;
			}
		}
		throw new BoundaryViolationException("This position has no left child");
	}

	@Override public Position<E> right(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		position = checkPosition(position);
		for (int i = 1; i < size(); i++) {
			if (position.equals(vector.get(i))) {
				Position<E> child = vector.get((2*i) + 1);
				if (child != null) {
					return child;
				}
				break;
			}
		}
		throw new BoundaryViolationException("This position has no right child");
	}

	@Override public boolean hasLeft(Position<E> position) throws InvalidPositionException {
		position = checkPosition(position);
		for (int i = 1; i < size(); i++) {
			if (position.equals(vector.get(i))) {
				Position<E> child = vector.get((2*i));
				return child != null;
			}
		}
		return false;
	}

	@Override public boolean hasRight(Position<E> position) throws InvalidPositionException {
		position = checkPosition(position);
		for (int i = 1; i < size(); i++) {
			if (position.equals(vector.get(i))) {
				Position<E> child = vector.get((2*i) + 1);
				return child != null;
			}
		}
		return false;
	}

	@Override public int size() {
		return size;
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public Iterable<Position<E>> positions() {
		List<Position<E>> list = new ArrayList<>();
		int i = 0;
		for (Position<E> position : vector) {
			if (position != null) {
				list.add(i++, position);
			}
		}
		return list;
	}

	@Override public E replace(Position<E> position, E element) throws InvalidPositionException {
		BTPosition<E> btPosition = checkPosition(position);
		for (int i = 1; i < size() + 1; i++) {
			if (btPosition.equals(vector.get(i))) {
				E temp = btPosition.getElement();
				btPosition.setElement(element);
				return temp;
			}
		}
		throw new InvalidPositionException("Could not find this position");
	}

	@Override public Position<E> root() throws EmptyTreeException {
		if (isEmpty()) {
			throw new EmptyTreeException("The tree is empty");
		}
		return vector.get(1);
	}

	@Override
	public Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		position = checkPosition(position);
		if (isRoot(position)) {
			throw new BoundaryViolationException("The root has no parent");
		}
		for (int i = 2; i < size(); i++) {
			if (position.equals(vector.get(i))) {
				Position<E> parent = vector.get(i/2);
				if (parent != null) {
					return parent;
				}
				break;
			}
		}
		throw new BoundaryViolationException("Could not find this position");
	}

	@Override
	public Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException {
		position = checkPosition(position);
		List<Position<E>> list = new ArrayList<>();
		for (int i = 1; i < size(); i++) {
			if (position.equals(vector.get(i))) {
				for (int j = i, k = 0; j < size(); j++) {
					Position<E> temp = vector.get(j);
					if (temp != null) {
						list.add(k++, temp);
					}
				}
				break;
			}
		}
		return list;
	}

	@Override public boolean isInternal(Position<E> position) throws InvalidPositionException {
		return hasLeft(position) || hasRight(position);
	}

	@Override public boolean isExternal(Position<E> position) throws InvalidPositionException {
		return !isInternal(position);
	}

	@Override public boolean isRoot(Position<E> position) throws InvalidPositionException {
		checkPosition(position);
		return position.equals(vector.get(1));
	}

	@Override public Iterator<E> iterator() {
		PositionList<E> elements = new NodePositionList<>();
		for (Position<E> position : positions())
			elements.addLast(position.getElement());
		return elements.iterator();
	}

	public Position<E> addRoot(E element) throws NonEmptyTreeException {
		if (size() > 0)
			throw new NonEmptyTreeException("The binary tree is not empty");
		BTPosition<E> root = new BTNode<>(element, null, null, null);
		vector.add(1, root);
		size++;
		return root;
	}
}
