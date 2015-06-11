package me.fahien.ds.set.genericmerge;

import java.util.Comparator;

import me.fahien.ds.positionlist.PositionList;

/** Union
 * @author Fahien */
public class Union<E> extends GenericMerge<E> {
	public Union(Comparator<E> comparator) {
		super(comparator);
	}

	@Override public void aIsLess(E element, PositionList<E> list) {
		list.addLast(element);
	}

	@Override public void bIsLess(E element, PositionList<E> list) {
		list.addLast(element);
	}

	@Override public void bothAreEqual(E a, E b, PositionList<E> list) {
		list.addLast(a);
	}
}