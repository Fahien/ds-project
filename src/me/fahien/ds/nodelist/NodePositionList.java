package me.fahien.ds.nodelist;

import java.util.Iterator;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyListException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.iterator.ElementIterator;
import me.fahien.ds.util.position.DLNode;
import me.fahien.ds.util.position.Position;

public class NodePositionList<E> implements PositionList<E> {
	protected int size;
	protected DLNode<E> header, trailer;

	public NodePositionList () {
		header = new DLNode<>(null, null, null);
		trailer = new DLNode<>(header, null, null);
		header.setNext(trailer);
	}

	protected DLNode<E> checkPosition (Position<E> position) throws InvalidPositionException {
		if (position == null)
			throw new InvalidPositionException("Position is null");
		if (position == header)
			throw new InvalidPositionException("Header is not a valid position");
		if (position == trailer)
			throw new InvalidPositionException("Trailer is not a valid position");
		try {
			DLNode<E> temp = (DLNode<E>) position;
			if ((temp.getPrev() == null) || (temp.getNext() == null))
				throw new InvalidPositionException("Invalid position");
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid type of position");
		}
	}

	@Override
	public int size () {
		return size;
	}

	@Override
	public boolean isEmpty () {
		return size == 0 && header.getNext() == trailer && trailer.getPrev() == header;
	}

	@Override
	public Position<E> addBefore (Position<E> position, E element) throws InvalidPositionException {
		DLNode<E> next = checkPosition(position);
		size++;
		DLNode<E> node = new DLNode<>(next.getPrev(), next, element);
		next.getPrev().setNext(node);
		next.setPrev(node);
		return node;
	}

	@Override
	public Position<E> addAfter (Position<E> position, E element) throws InvalidPositionException {
		DLNode<E> prev = checkPosition(position);
		size++;
		DLNode<E> node = new DLNode<>(prev, prev.getNext(), element);
		prev.getNext().setPrev(node);
		prev.setNext(node);
		return node;
	}

	@Override
	public void addFirst (E element) {
		size++;
		DLNode<E> node = new DLNode<>(header, header.getNext(), element);
		header.getNext().setPrev(node);
		header.setNext(node);
	}

	@Override
	public void addLast (E element) {
		size++;
		DLNode<E> node = new DLNode<>(trailer.getPrev(), trailer, element);
		trailer.getPrev().setNext(node);
		trailer.setPrev(node);
	}

	@Override
	public E remove (Position<E> position) throws InvalidPositionException {
		DLNode<E> removed = checkPosition(position);
		size--;
		removed.getPrev().setNext(removed.getNext());
		removed.getNext().setPrev(removed.getPrev());
		removed.setPrev(null);
		removed.setNext(null);
		return removed.getElement();
	}

	@Override
	public E set (Position<E> position, E element) throws InvalidPositionException {
		DLNode<E> node = checkPosition(position);
		E replace = node.getElement();
		node.setElement(element);
		return replace;
	}

	@Override
	public Position<E> first () throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("The list is empty");
		return header.getNext();
	}

	@Override
	public Position<E> last () throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("The list is empty");
		return trailer.getPrev();
	}

	@Override
	public Position<E> prev (Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		DLNode<E> node = checkPosition(position);
		if (node.getPrev() == header)
			throw new BoundaryViolationException("There is not element before the first one");
		return node.getPrev();
	}

	@Override
	public Position<E> next (Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		DLNode<E> node = checkPosition(position);
		if (node.getNext() == trailer)
			throw new BoundaryViolationException("There is not element after the last one");
		return node.getPrev();
	}

	@Override
	public String toString () {
		String string = "[";
		if (!isEmpty()) {
			DLNode<E> node = header.getNext();
			string += node.getElement();
			for (node = node.getNext(); node != null; node = node.getNext()) {
				string += ", " + node.getElement();
			}
		}
		string += "]";
		return string;
	}

	public void reverse () {
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

	@Override
	public Iterator<E> iterator () {
		return new ElementIterator<>(this);
	}

	@Override
	public Iterable<Position<E>> getPositions () {
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