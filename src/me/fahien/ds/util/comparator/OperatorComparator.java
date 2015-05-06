package me.fahien.ds.util.comparator;

import java.util.Comparator;

import me.fahien.ds.util.operator.SimpleOperator;

/** Comparator for operators
 * @author Fahien */
public class OperatorComparator<E extends SimpleOperator> implements Comparator<E> {
	@Override public int compare(E a, E b) {
		return a.compareWith(b);
	}
}
