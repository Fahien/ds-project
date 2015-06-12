package me.fahien.ds.set.genericmerge;

import java.util.Comparator;

import me.fahien.ds.nodelist.PositionList;

/** Intersect
 * @author Fahien */
public class Intersect<E> extends GenericMerge<E> {
	public Intersect(Comparator<E> comparator) {
		super(comparator);
	}

	@Override public void bothAreEqual(E a, E b, PositionList<E> list) {
		list.addLast(a);
	}
}