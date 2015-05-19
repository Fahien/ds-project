package me.fahien.ds.set.genericmerge;

import java.util.Comparator;

import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.set.OrderedListSet;

/** Generic Merge
 * @author Fahien */
public abstract class GenericMerge<E> {
	private Comparator<E> comparator;

	public GenericMerge(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public PositionList<E> genericMerge(OrderedListSet<E> setA, OrderedListSet<E> setB) {
		PositionList<E> list = new NodePositionList<>();
		while(!setA.isEmpty() && !setB.isEmpty()) {
			E a = setA.first().getElement();
			E b = setB.first().getElement();
			if (comparator.compare(a, b) < 0) {
				aIsLess(a, list);
				setA.remove(setA.first());
			} else if (comparator.compare(a, b) > 0) {
				bIsLess(b, list);
				setB.remove(setB.first());
			} else { // b == a
				bothAreEqual(a, b, list);
				setA.remove(setA.first());
				setB.remove(setB.first());
			}
		}
		while (!setA.isEmpty()) {
			aIsLess(setA.first().getElement(), list);
			setA.remove(setA.first());
		}while (!setB.isEmpty()) {
			bIsLess(setB.first().getElement(), list);
			setB.remove(setB.first());
		}
		return list;
	}

	public void aIsLess(E element, PositionList<E> list) {}
	public void bIsLess(E element, PositionList<E> list) {}
	public void bothAreEqual(E a, E b, PositionList<E> list) {}
}