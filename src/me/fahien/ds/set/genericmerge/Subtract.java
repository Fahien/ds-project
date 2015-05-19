package me.fahien.ds.set.genericmerge;

import java.util.Comparator;

import me.fahien.ds.positionlist.PositionList;

/** Subtract
 * @author Fahien */
public class Subtract<E> extends GenericMerge<E> {
	public Subtract(Comparator<E> comparator) {
		super(comparator);
	}

	@Override public void aIsLess(E element, PositionList<E> list) {
		list.addLast(element);
	}
}