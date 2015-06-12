package me.fahien.ds.sequence;

import me.fahien.ds.exception.EmptySequenceException;
import me.fahien.ds.exception.IndexOutOfBoundsException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.util.position.DLNode;
import me.fahien.ds.util.position.Position;

public class NodeSequence<E> extends NodePositionList<E> implements Sequence<E> {

	/** Checks whether the given index is in range [0, n - 1] */
	protected void checkIndex(int index, int n) throws IndexOutOfBoundsException {
		if (index < 0 || index >= n)
			throw new IndexOutOfBoundsException("Illegal index of " + index);
	}

	@Override public E remove(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		return remove(atIndex(index));
	}

	@Override public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index == size()) {
			addLast(element);
		} else {
			checkIndex(index, size());
			addBefore(atIndex(index), element);
		}
	}

	@Override public E set(int index, E element) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		return set(atIndex(index), element);
	}

	@Override public E get(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		return atIndex(index).getElement();
	}

	@Override public Position<E> atIndex(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		DLNode<E> node;
		if (index <= size() / 2) {
			node = getHeader().getNext();
			for (int i = 0; i < index; i++) {
				node = node.getNext();
			}
		} else {
			node = getTrailer().getPrev();
			for (int i = 1; i < size() - index; i++) {
				node = node.getPrev();
			}
		}
		return node;
	}

	@Override public int indexOf(Position<E> position) throws InvalidPositionException {
		checkPosition(position);
		DLNode<E> left = getHeader().getNext();
		DLNode<E> right = getTrailer().getPrev();
		int i = 0;
		for (; i < size() / 2; i++, left = left.getNext(), right = right.getPrev()) {
			if (left == position) {
				return i;
			}
			else if (right == position) {
				return size() - 1 - i;
			}
		}
		return i;
	}

	@Override public E getFirst() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException();
		return getHeader().getNext().getElement();
	}

	@Override public E getLast() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException();
		return getTrailer().getPrev().getElement();
	}

	@Override public E removeFirst() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException();
		return remove(getHeader().getNext());
	}

	@Override public E removeLast() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException();
		return remove(getTrailer().getPrev());
	}

	@Override public String toString() {
		String string = "[";
		if (!isEmpty()) {
			DLNode<E> node = getHeader().getNext();
			string += node.getElement();
			for (node = node.getNext(); node != getTrailer(); node = node.getNext()) {
				string += ", " + node.getElement();
			}
		}
		string += "]";
		return string;
	}
}