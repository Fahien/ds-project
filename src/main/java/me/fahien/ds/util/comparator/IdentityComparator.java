package me.fahien.ds.util.comparator;

import java.util.Comparator;

public class IdentityComparator<E> implements Comparator<E> {
	@Override public int compare(E a, E b) throws ClassCastException {
		return a == b ? 0 : 1;
	}
}