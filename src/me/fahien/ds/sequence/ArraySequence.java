package me.fahien.ds.sequence;

import me.fahien.ds.arraylist.ArrayList;
import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyListException;
import me.fahien.ds.exception.EmptySequenceException;
import me.fahien.ds.exception.FullSequenceException;
import me.fahien.ds.exception.IndexOutOfBoundsException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.position.ArrayPosition;
import me.fahien.ds.position.Position;

public class ArraySequence<E> implements Sequence<E> {
	private static final int CAPACITY = 1024;

	private int capacity;
	private ArrayList<ArrayPosition<E>> array;

	public ArraySequence () {
		this(CAPACITY);
	}

	public ArraySequence (int capacity) {
		this.capacity = capacity;
		array = new ArrayList<ArrayPosition<E>>(capacity);
	}

	@Override
	public Position<E> atIndex(int index) throws BoundaryViolationException {
		checkIndex(index);
		return array.get(index);
	}

	@Override
	public int indexOf(Position<E> position) throws InvalidPositionException {
		return checkPosition(position).getIndex();
	}

	private void checkIndex(int index) throws BoundaryViolationException {
		if (index < 0 || index >= size())
			throw new BoundaryViolationException("Illegal index of "+ index);
	}

	private ArrayPosition<E> checkPosition(Position<E> position) {
		if (position == null)
			throw new InvalidPositionException("Position is null");
		try {
			return (ArrayPosition<E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid type of position");
		}
	}

	@Override
	public int size() {
		return array.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Position<E> addBefore(Position<E> position, E element) throws InvalidPositionException {
		if (size() == capacity)
			throw new FullSequenceException("Sequence is full");
		checkPosition(position);
		ArrayPosition<E> arrayPosition = new ArrayPosition<E>(indexOf(position), element);
		array.add(indexOf(position), arrayPosition);
		for (int i = indexOf(position) + 1; i < size(); i++) {
			array.get(i).setIndex(i);
		}
		return arrayPosition;
	}

	@Override
	public Position<E> addAfter(Position<E> position, E element) throws InvalidPositionException {
		if (size() == capacity)
			throw new FullSequenceException("Sequence is full");
		checkPosition(position);
		ArrayPosition<E> arrayPosition = new ArrayPosition<E>(indexOf(position) + 1, element);
		array.add(indexOf(position) + 1, arrayPosition);
		for (int i = indexOf(position) + 2; i < size(); i++) {
			array.get(i).setIndex(i);
		}
		return arrayPosition;
	}

	@Override
	public void addFirst(E element) {
		if (size() == capacity)
			throw new FullSequenceException("Sequence is full");
		ArrayPosition<E> arrayPosition = new ArrayPosition<E>(0, element);
		array.add(0, arrayPosition);
		for (int i = 0; i < size(); i++) {
			array.get(i).setIndex(i);
		}
	}

	@Override
	public void addLast(E element) {
		if (size() == capacity)
			throw new FullSequenceException("Sequence is full");
		array.add(size(), new ArrayPosition<E>(size(), element));
	}

	@Override
	public E remove(Position<E> position) throws InvalidPositionException {
		checkPosition(position);
		ArrayPosition<E> arrayPosition = array.remove(indexOf(position));
		for (int i = indexOf(position); i < size(); i++) {
			array.get(i).setIndex(i);
		}
		return arrayPosition.getElement();
	}

	@Override
	public E set(Position<E> position, E element) throws InvalidPositionException {
		checkPosition(position);
		ArrayPosition<E> arrayPosition = array.get(indexOf(position));
		arrayPosition.setElement(element);
		array.set(indexOf(position), arrayPosition);
		return element;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("The sequence is empty");
		return array.get(0);
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("The sequence is empty");
		return array.get(size() - 1);
	}

	@Override
	public Position<E> prev(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		return array.get(indexOf(checkPosition(position)) - 1);
	}

	@Override
	public Position<E> next(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
		return array.get(indexOf(checkPosition(position)) + 1);
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		checkIndex(index);
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

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (size() == capacity)
			throw new FullSequenceException("Sequence is full");
		checkIndex(index);
		addBefore(atIndex(index), element);
	}

	@Override
	public E set(int index, E element) throws IndexOutOfBoundsException {
		checkIndex(index);
		return set(atIndex(index), element);
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		checkIndex(index);
		return array.get(index).getElement();
	}

	@Override
	public E getFirst() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException("The sequence is empty");
		return array.get(0).getElement();
	}

	@Override
	public E getLast() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException("The sequence is empty");
		return array.get(size() - 1).getElement();
	}

	@Override
	public E removeFirst() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException("The sequence is empty");
		return remove(0);
	}

	@Override
	public E removeLast() throws EmptySequenceException {
		if (isEmpty())
			throw new EmptySequenceException("The sequence is empty");
		return remove(size() - 1);
	}
}