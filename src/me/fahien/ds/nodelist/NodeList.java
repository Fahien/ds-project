package me.fahien.ds.nodelist;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyListException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.position.DNode;
import me.fahien.ds.position.Position;

public class NodeList<E> implements PositionList<E> {
	protected int size;
	protected DNode<E> header, trailer;

	public NodeList () {
		header = new DNode<E>(null, null, null);
		trailer = new DNode<E>(header, null, null);
		header.setNext(trailer);
	}

	protected DNode<E> checkPosition (Position<E> position) throws InvalidPositionException {
		if (position == null)
			throw new InvalidPositionException("Position is null");
		if (position == header)
			throw new InvalidPositionException("Header is not a valid position");
		if (position == trailer)
			throw new InvalidPositionException("Trailer is not a valid position");
		try {
			DNode<E> temp = (DNode<E>) position;
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
		DNode<E> next = checkPosition(position);
		size++;
		DNode<E> node = new DNode<E>(next.getPrev(), next, element);
		next.getPrev().setNext(node);
		next.setPrev(node);
		return node;
	}

	@Override
	public Position<E> addAfter (Position<E> position, E element) throws InvalidPositionException {
		DNode<E> prev = checkPosition(position);
		size++;
		DNode<E> node = new DNode<E>(prev, prev.getNext(), element);
		prev.getNext().setPrev(node);
		prev.setNext(node);
		return node;
	}

	@Override
	public void addFirst (E element) {
		size++;
		DNode<E> node = new DNode<E>(header, header.getNext(), element);
		header.getNext().setPrev(node);
		header.setNext(node);
	}

	@Override
	public void addLast (E element) {
		size++;
		DNode<E> node = new DNode<E>(trailer.getPrev(), trailer, element);
		trailer.getPrev().setNext(node);
		trailer.setPrev(node);
	}

	@Override
	public E remove (Position<E> position) throws InvalidPositionException {
		DNode<E> removed = checkPosition(position);
		size--;
		removed.getPrev().setNext(removed.getNext());
		removed.getNext().setPrev(removed.getPrev());
		removed.setPrev(null);
		removed.setNext(null);
		return removed.getElement();
	}

	@Override
	public E set (Position<E> position, E element) throws InvalidPositionException {
		DNode<E> node = checkPosition(position);
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
		DNode<E> node = checkPosition(position);
		if (node.getPrev() == header)
			throw new BoundaryViolationException("There is not element before the first one");
		return node.getPrev();
	}

	@Override
	public Position<E> next (Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> node = checkPosition(position);
		if (node.getNext() == trailer)
			throw new BoundaryViolationException("There is not element after the last one");
		return node.getPrev();
	}

	@Override
	public String toString () {
		String string = "[";
		if (!isEmpty()) {
			DNode<E> node = header.getNext();
			string += node.getElement();
			for (node = node.getNext(); node.getNext() != null; node = node.getNext()) {
				string += ", " + node.getElement();
			}
		}
		return string += "]";
	}

	public void reverse () {
		if (!isEmpty() && size > 1) {
			DNode<E> left = header.getNext();
			DNode<E> right = trailer.getPrev();
			for (int i = 0; i < size / 2 && left != right; i++, left = left.getNext(), right = right.getPrev()) {
				E temp = left.getElement();
				left.setElement(right.getElement());
				right.setElement(temp);
			}
		}
	}
}