package me.fahien.ds.tree.binarytree.completebinarytree;

import java.util.Iterator;

import me.fahien.ds.list.ArrayList;
import me.fahien.ds.list.List;
import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.position.BTIndexedPosition;
import me.fahien.ds.util.position.Position;

public class ArrayListCompleteBinaryTree<E> implements CompleteBinaryTree<E> {
	List<BTIndexedPosition<E>> tree = new ArrayList<>();

	public ArrayListCompleteBinaryTree() {
		tree.add(0, null);
	}

	protected BTIndexedPosition<E> checkPosition(Position<E> position) throws InvalidPositionException {
		if (position == null)
			throw new InvalidPositionException("The position is null");
		try {
			return (BTIndexedPosition<E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid type of position");
		}
	}

	protected void preorderPositions(Position<E> position, PositionList<Position<E>> positionList) throws InvalidPositionException {
		positionList.addLast(position);
		if (hasLeft(position))
			preorderPositions(left(position), positionList);
		if (hasRight(position))
			preorderPositions(right(position), positionList);
	}

	@Override public Position<E> left(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		if (!hasLeft(position))
			throw new BoundaryViolationException("The position has no left child");
		return tree.get(checkPosition(position).getIndex() * 2);

	}

	@Override public Position<E> right(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		if (!hasLeft(position))
			throw new BoundaryViolationException("The position has no right child");
		return tree.get(checkPosition(position).getIndex() * 2 + 1);
	}

	@Override public boolean hasLeft(Position<E> position) throws InvalidPositionException {
		BTIndexedPosition<E> indexedPosition = checkPosition(position);
		return 2 * indexedPosition.getIndex() <= size();
	}

	@Override public boolean hasRight(Position<E> position) throws InvalidPositionException {
		BTIndexedPosition<E> indexedPosition = checkPosition(position);
		return 2 * indexedPosition.getIndex() + 1 <= size();
	}

	@Override public int size() {
		return tree.size() - 1;
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public Iterable<Position<E>> positions() {
		PositionList<Position<E>> list = new NodePositionList<>();
		for (int i = 1; i < tree.size(); i++) {
			list.addLast(tree.get(i));
		}
		return list;
	}

	@Override public E replace(Position<E> position, E element) throws InvalidPositionException {
		BTIndexedPosition<E> indexedPosition = checkPosition(position);
		E temp = indexedPosition.getElement();
		indexedPosition.setElement(element);
		return temp;
	}

	@Override public Position<E> root() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException("The complete binary tree is empty");
		return tree.get(1);
	}

	@Override public Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		BTIndexedPosition<E> indexedPosition = checkPosition(position);
		if (isRoot(indexedPosition)) {
			throw new BoundaryViolationException("The root has no parent");
		}
		return tree.get(indexedPosition.getIndex() / 2);
	}

	@Override public Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException {
		PositionList<Position<E>> positionList = new NodePositionList<>();
		if (size() != 0)
			preorderPositions(position, positionList);
		return positionList;
	}

	@Override public boolean isInternal(Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getIndex() * 2 < size();
	}

	@Override public boolean isExternal(Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getIndex() * 2 > size();
	}

	@Override public boolean isRoot(Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getIndex() == 1;
	}

	@Override public Iterator<E> iterator() {
		PositionList<E> list = new NodePositionList<>();
		for (int i = 1; i < tree.size(); i++) {
			list.addLast(tree.get(i).getElement());
		}
		return list.iterator();
	}

	@Override public Position<E> add(E element) {
		int index = size() + 1;
		BTIndexedPosition<E> indexedPosition = new BTIndexedPosition<>(element, index);
		tree.add(index, indexedPosition);
		return indexedPosition;
	}

	@Override public E remove() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException("The tree is empty");
		return tree.remove(size()).getElement();
	}
}