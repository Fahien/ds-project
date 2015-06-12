package me.fahien.ds.sequence;

import java.util.Iterator;

import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.arraylist.ArrayIndexList;
import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyListException;
import me.fahien.ds.exception.EmptySequenceException;
import me.fahien.ds.exception.FullSequenceException;
import me.fahien.ds.exception.IndexOutOfBoundsException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.iterator.ElementIterator;
import me.fahien.ds.util.position.ArrayPosition;
import me.fahien.ds.util.position.Position;

public class ArraySequence<E> implements Sequence<E> {
	private static final int CAPACITY = 1024;

	private int capacity;
	private ArrayIndexList<ArrayPosition<E>> array;

	public ArraySequence() {
		this(CAPACITY);
	}

	public ArraySequence(int capacity) {
		this.capacity = capacity;
		array = new ArrayIndexList<>(capacity);
	}

	/** Checks whether the given index is in range [0, n - 1] */
	private void checkIndex(int index, int n) throws IndexOutOfBoundsException {
		if (index < 0 || index >= n)
			throw new IndexOutOfBoundsException("Illegal index of "+ index);
	}

	/** Checks whether the given position is valid */
	private ArrayPosition<E> checkPosition(Position<E> position) {
		if (position == null)
			throw new InvalidPositionException("Position is null");
		try {
			return (ArrayPosition<E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid type of position");
		}
	}

	@Override public Position<E> atIndex(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		return array.get(index);
	}

	@Override public int indexOf(Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getIndex();
	}

	@Override public int size() {
		return array.size();
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public Position<E> addBefore(Position<E> position, E element) throws InvalidPositionException {
		if (size() == capacity)
			throw new FullSequenceException();
		checkPosition(position);
		ArrayPosition<E> arrayPosition = new ArrayPosition<>(indexOf(position), element);
		array.add(indexOf(position), arrayPosition);
		for (int i = indexOf(position) + 1; i < size(); i++) {
			array.get(i).setIndex(i);
		}
		return arrayPosition;
	}

	@Override public Position<E> addAfter(Position<E> position, E element) throws InvalidPositionException {
		if (size() == capacity)
			throw new FullSequenceException();
		checkPosition(position);
		ArrayPosition<E> arrayPosition = new ArrayPosition<>(indexOf(position) + 1, element);
		array.add(indexOf(position) + 1, arrayPosition);
		for (int i = indexOf(position) + 2; i < size(); i++) {
			array.get(i).setIndex(i);
		}
		return arrayPosition;
	}

	@Override public void addFirst(E element) {
		if (size() == capacity)
			throw new FullSequenceException();
		ArrayPosition<E> arrayPosition = new ArrayPosition<>(0, element);
		array.add(0, arrayPosition);
		for (int i = 0; i < size(); i++) {
			array.get(i).setIndex(i);
		}
	}

	@Override public void addLast(E element) {
		if (size() == capacity)
			throw new FullSequenceException();
		array.add(size(), new ArrayPosition<>(size(), element));
	}

	@Override public E remove(Position<E> position) throws InvalidPositionException {
		checkPosition(position);
		ArrayPosition<E> arrayPosition = array.remove(indexOf(position));
		for (int i = indexOf(position); i < size(); i++) {
			array.get(i).setIndex(i);
		}
		return arrayPosition.getElement();
	}

	@Override public E set(Position<E> position, E element) throws InvalidPositionException {
		checkPosition(position);
		ArrayPosition<E> arrayPosition = array.get(indexOf(position));
		arrayPosition.setElement(element);
		array.set(indexOf(position), arrayPosition);
		return element;
	}

	@Override public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("The sequence is empty");
		return array.get(0);
	}

	@Override public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("The sequence is empty");
		return array.get(size() - 1);
	}

	@Override public Position<E> prev(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		return array.get(indexOf(checkPosition(position)) - 1);
	}

	@Override public Position<E> next(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		return array.get(indexOf(checkPosition(position)) + 1);
	}

	@Override public E remove(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		try {
			if (index == 0)
				return removeFirst();
			else if (index == size() - 1)
				return removeLast();
			else
				return remove(atIndex(index));
		} catch (EmptySequenceException e) {
			throw new IndexOutOfBoundsException("The index is out of bounds");
		}
	}

	@Override public void add(int index, E element) throws IndexOutOfBoundsException {
		if (size() == capacity)
			throw new FullSequenceException();
		checkIndex(index, size());
		addBefore(atIndex(index), element);
	}

	@Override public E set(int index, E element) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		return set(atIndex(index), element);
	}

	@Override public E get(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size());
		return array.get(index).getElement();
	}

	@Override public E getFirst() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException();
		return array.get(0).getElement();
	}

	@Override public E getLast() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException();
		return array.get(size() - 1).getElement();
	}

	@Override public E removeFirst() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException();
		return remove(atIndex(0));
	}

	@Override public E removeLast() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException();
		return remove(atIndex(size() - 1));
	}

	@Override public String toString() {
		String string = "[";
		if (!isEmpty()) {
			string += array.get(0).getElement();
			for (int i = 1; i < array.size() && array.get(i) != null && i < size(); i++) {
				string += ", " + array.get(i).getElement();
			}
		}
		string += "]";
		return string;
	}

	@Override public Iterator<E> iterator() {
		return new ElementIterator<>(this);
	}

	@Override public Iterable<Position<E>> positions() {
		PositionList<Position<E>> list = new NodePositionList<>();
		for (int i = 0; i < array.size() - 1; i++) {
			list.addLast(array.get(i));
		}
		return list;
	}
}