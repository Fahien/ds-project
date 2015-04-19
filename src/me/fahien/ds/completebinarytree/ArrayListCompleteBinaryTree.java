package me.fahien.ds.completebinarytree;

import java.util.Iterator;

import me.fahien.ds.arraylist.IndexList;
import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.position.BTIndexedPosition;
import me.fahien.ds.util.position.Position;

public class ArrayListCompleteBinaryTree<E> implements CompleteBinaryTree<E> {
	IndexList<BTIndexedPosition<E>> tree;

	protected BTIndexedPosition<E> checkPosition (Position<E> position) throws InvalidPositionException {
		if (position == null)
			throw new InvalidPositionException("The position is null");
		try {
			return (BTIndexedPosition<E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid type of position");
		}
	}

	protected void preorderPositions (Position<E> position, PositionList<Position<E>> positionList) throws InvalidPositionException {
		positionList.addLast(position);
		if (hasLeft(position))
			preorderPositions(getLeft(position), positionList);
		if (hasRight(position))
			preorderPositions(getRight(position), positionList);
	}

	@Override
	public Position<E> getLeft (Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		if (!hasLeft(position))
			throw new BoundaryViolationException("The position has no left child");
		return tree.get(checkPosition(position).getIndex() * 2);
		
	}

	@Override
	public Position<E> getRight (Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		if (!hasLeft(position))
			throw new BoundaryViolationException("The position has no right child");
		return tree.get(checkPosition(position).getIndex() * 2 + 1);
	}

	@Override
	public boolean hasLeft (Position<E> position) throws InvalidPositionException {
		BTIndexedPosition<E> indexedPosition = checkPosition(position);
		return 2 * indexedPosition.getIndex() <= size();
	}

	@Override
	public boolean hasRight (Position<E> position) throws InvalidPositionException {
		BTIndexedPosition<E> indexedPosition = checkPosition(position);
		return 2 * indexedPosition.getIndex() + 1 <= size();
	}

	@Override
	public int size () {
		return tree.size() - 1; // because the bind value at index 0 is null
	}

	@Override
	public boolean isEmpty () {
		return size() < 2;
	}

	@Override
	public Iterable<Position<E>> getPositions () {
		PositionList<Position<E>> positionList = new NodePositionList<>();
		for (int i = 1; i < tree.size(); i++) {
			positionList.addLast(tree.get(i));
		}
		return positionList;
	}

	@Override
	public E replace (Position<E> position, E element) throws InvalidPositionException {
		BTIndexedPosition<E> indexedPosition = checkPosition(position);
		E temp = indexedPosition.getElement();
		indexedPosition.setElement(element);
		return temp;
	}

	@Override
	public Position<E> getRoot () throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException("The complete binary tree is empty");
		return tree.get(1);
	}

	@Override
	public Position<E> parentOf (Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		BTIndexedPosition<E> indexedPosition = checkPosition(position);
		if (isRoot(indexedPosition)) {
			throw new BoundaryViolationException("The root has no parent");
		}
		return tree.get(indexedPosition.getIndex() / 2);
	}

	@Override
	public Iterable<Position<E>> childrenOf (Position<E> position) throws InvalidPositionException {
		PositionList<Position<E>> positionList = new NodePositionList<>();
		if (size() != 0)
			preorderPositions(position, positionList);
		return positionList;
	}

	@Override
	public boolean isInternal (Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getIndex() * 2 < size();
	}

	@Override
	public boolean isExternal (Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getIndex() * 2 > size();
	}

	@Override
	public boolean isRoot (Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getIndex() == 1;
	}

	@Override
	public Iterator<E> iterator () {
		PositionList<E> positionList = new NodePositionList<>();
		for (int i = 1; i < tree.size(); i++) {
			positionList.addLast(tree.get(i).getElement());
		}
		return positionList.iterator();
	}

	@Override
	public Position<E> add (E element) {
		int index = size() + 1;
		BTIndexedPosition<E> indexedPosition = new BTIndexedPosition<>(element, index);
		tree.add(index, indexedPosition);
		return indexedPosition;
	}

	@Override
	public E remove () throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException("The tree is empty");
		return tree.remove(size()).getElement();
	}
}