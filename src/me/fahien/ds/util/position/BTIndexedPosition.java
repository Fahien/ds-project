package me.fahien.ds.util.position;

public class BTIndexedPosition<E> implements Position<E> {
	private E element;
	private int index;

	/** @param element	The element
	 * @param index	The index */
	public BTIndexedPosition(E element, int index) {
		this.element = element;
		this.index = index;
	}

	@Override
	/** @return The element */
	public E getElement () {
		return element;
	}

	/** @param The element */
	public void setElement (E element) {
		this.element = element;
	}

	/** @return The index */
	public int getIndex () {
		return index;
	}

}
