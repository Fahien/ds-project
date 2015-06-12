package me.fahien.ds.util.position.graph;

import me.fahien.ds.map.HashTableMap;

/** Graph position
 * @author Fahien */
public class GraphPosition<E> extends HashTableMap<Object, Object> implements DecorablePosition<E> {
	private E element;

	@Override public E getElement() {
		return element;
	}

	/* Updates the element */
	public void setElement(E element) {
		this.element = element;
	}
}