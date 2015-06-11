package me.fahien.ds.deque;

import me.fahien.ds.exception.EmptyDequeException;
import me.fahien.ds.list.ArrayList;
import me.fahien.ds.list.List;

/** Example of the Adapter pattern.
 * This is an implementation of a {@link me.fahien.ds.deque.Deque}
 * using a {@link me.fahien.ds.list.List} as storage
 * @author Fahien */
public class ListDeque<E> implements Deque<E>{
	private List<E> list;

	public ListDeque() {
		list = new ArrayList<>();
	}

	@Override public int size() {
		return list.size();
	}

	@Override public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override public E getFirst() throws EmptyDequeException {
		return list.get(0);
	}

	@Override public E getLast() throws EmptyDequeException {
		return list.get(size() - 1);
	}

	@Override public void addFirst(E element) {
		list.add(0, element);
	}

	@Override public void addLast(E element) {
		list.add(size(), element);
	}

	@Override public E removeFirst() throws EmptyDequeException {
		return list.remove(0);
	}

	@Override public E removeLast() throws EmptyDequeException {
		return list.remove(size() - 1);
	}

	@Override public String toString() {
		return list.toString();
	}
}
