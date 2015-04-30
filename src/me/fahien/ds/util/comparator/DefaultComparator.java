package me.fahien.ds.util.comparator;

import java.util.Comparator;

public class DefaultComparator<Key> implements Comparator<Key> {
	@SuppressWarnings("unchecked")
	@Override public int compare (Key a, Key b) throws ClassCastException {
		return((Comparable<Key>)a).compareTo(b);
	}
}