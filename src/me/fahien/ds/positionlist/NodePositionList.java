package me.fahien.ds.positionlist;

import java.util.Iterator;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyListException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.iterator.ElementIterator;
import me.fahien.ds.util.position.DLNode;
import me.fahien.ds.util.position.Position;

public class NodePositionList<E> implements PositionList<E> {
	private int size;
	private DLNode<E> header;
	private DLNode<E> trailer;

	public NodePositionList() {
		header = new DLNode<>(null, null, null);
		trailer = new DLNode<>(header, null, null);
		header.setNext(trailer);
	}

	/** Returns the header */
	protected DLNode<E> getHeader() {
		return header;
	}

	/** Returns the trailer */
	protected DLNode<E> getTrailer() {
		return trailer;
	}

	/** Validates the position and returns it as a node */
	protected DLNode<E> checkPosition(Position<E> position) throws InvalidPositionException {
		if (position == null)
			throw new InvalidPositionException("The position is null");
		if (position == header)
			throw new InvalidPositionException("The header is not a valid position");
		if (position == trailer)
			throw new InvalidPositionException("The trailer is not a valid position");
		try {
			DLNode<E> temp = (DLNode<E>)position;
			if ((temp.getPrev() == null) || (temp.getNext() == null))
				throw new InvalidPositionException("This position is no longer in the list");
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid type of position");
		}
	}

	@Override public int size() {
		return size;
	}

	@Override public boolean isEmpty() {
		return size == 0 && header.getNext() == trailer && trailer.getPrev() == header;
	}

	/** Adds an element to the list between the given nodes */
	private Position<E> addBetween(E element, DLNode<E> prev, DLNode<E> next) {
		DLNode<E> node = new DLNode<>(prev, next, element);
		prev.setNext(node);
		next.setPrev(node);
		size++;
		return node;
	}

	@Override public Position<E> addBefore(Position<E> position, E element) throws InvalidPositionException {
		DLNode<E> next = checkPosition(position);
		return addBetween(element, next.getPrev(), next);
	}

	@Override public Position<E> addAfter(Position<E> position, E element) throws InvalidPositionException {
		DLNode<E> prev = checkPosition(position);
		return addBetween(element, prev, prev.getNext());
	}

	@Override public void addFirst(E element) {
		addBetween(element, header, header.getNext());
	}

	@Override public void addLast(E element) {
		addBetween(element, trailer.getPrev(), trailer);
	}

	@Override public E remove(Position<E> position) throws InvalidPositionException {
		DLNode<E> removed = checkPosition(position);
		size--;
		removed.getPrev().setNext(removed.getNext());
		removed.getNext().setPrev(removed.getPrev());
		E element = removed.getElement();
		removed.setPrev(null);
		removed.setNext(null);
		removed.setElement(null);
		return element;
	}

	@Override public E set(Position<E> position, E element) throws InvalidPositionException {
		DLNode<E> node = checkPosition(position);
		E replace = node.getElement();
		node.setElement(element);
		return replace;
	}

	@Override public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("The list is empty");
		return header.getNext();
	}

	@Override public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("The list is empty");
		return trailer.getPrev();
	}

	@Override public Position<E> prev(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		DLNode<E> node = checkPosition(position);
		if (node.getPrev() == header)
			throw new BoundaryViolationException("There is not element before the first one");
		return node.getPrev();
	}

	@Override public Position<E> next(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		DLNode<E> node = checkPosition(position);
		if (node.getNext() == trailer)
			throw new BoundaryViolationException("There is not element after the last one");
		return node.getNext();
	}

	@Override public String toString() {
		String string = "[";
		if (!isEmpty()) {
			DLNode<E> node = header.getNext();
			string += node.getElement();
			for (node = node.getNext(); node != trailer; node = node.getNext()) {
				string += ", " + node.getElement();
			}
		}
		string += "]";
		return string;
	}

	public void reverse() {
		if (!isEmpty() && size > 1) {
			DLNode<E> left = header.getNext();
			DLNode<E> right = trailer.getPrev();
			for (int i = 0; i < size / 2 && left != right; i++, left = left.getNext(), right = right.getPrev()) {
				E temp = left.getElement();
				left.setElement(right.getElement());
				right.setElement(temp);
			}
		}
	}

	@Override public Iterator<E> iterator() {
		return new ElementIterator<>(this);
	}

	@Override public Iterable<Position<E>> getPositions() {
		PositionList<Position<E>> list = new NodePositionList<>();
		if(!isEmpty()) {
			Position<E> position = first();
			for (int i = 0; i < size - 1; i++) {
				list.addLast(position);
				position = next(position);
			}
			list.addLast(last());
		}
		return list;
	}
}