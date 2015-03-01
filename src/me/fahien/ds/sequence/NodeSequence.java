package me.fahien.ds.sequence;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptySequenceException;
import me.fahien.ds.exception.IndexOutOfBoundsException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.nodelist.NodeList;
import me.fahien.ds.position.DNode;
import me.fahien.ds.position.Position;

public class NodeSequence<E> extends NodeList<E> implements Sequence<E> {

	protected void checkIndex (int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Illegal index of "+ index);
	}

	@Override
	public E remove (int index) throws IndexOutOfBoundsException {
		checkIndex(index);
		return remove(atIndex(index));
	}

	@Override
	public void add (int index, E element) throws IndexOutOfBoundsException {
		if (index == size) {
			addLast(element);
		} else {
			checkIndex(index);
			addBefore(atIndex(index), element);
		}
	}

	@Override
	public E set (int index, E element) throws IndexOutOfBoundsException {
		checkIndex(index);
		return set(atIndex(index), element);
	}

	@Override
	public E get (int index) throws IndexOutOfBoundsException {
		checkIndex(index);
		return atIndex(index).getElement();
	}

	@Override
	public Position<E> atIndex (int index) throws BoundaryViolationException {
		checkIndex(index);
		DNode<E> node;
		if (index <= size /2) {
			node = header.getNext();
			for (int i = 0; i < index; i++) {
				node = node.getNext();
			}
		} else {
			node = trailer.getPrev();
			for (int i = 1; i < size - index; i++) {
				node = node.getPrev();
			}
		}
		return node;
	}

	@Override
	public int indexOf (Position<E> position) throws InvalidPositionException {
		checkPosition(position);
		DNode<E> left = header.getNext();
		DNode<E> right = trailer.getPrev();
		int i = 0;
		for (; i < size / 2; i++, left = left.getNext(), right = right.getPrev()) {
			if (left == position) {
				return i;
			}
			else if (right == position) {
				return size - 1 - i;
			}
		}
		return i;
	}

	@Override
	public E getFirst () throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException("The sequence is empty");
		return header.getNext().getElement();
	}

	@Override
	public E getLast () throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException("The sequence is empty");
		return trailer.getPrev().getElement();
	}

	@Override
	public E removeFirst () throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException("The sequence is empty");
		return remove(header.getNext());
	}

	@Override
	public E removeLast () throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException("The sequence is empty");
		return remove(trailer.getPrev());
	}
}