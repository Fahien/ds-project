package me.fahien.ds.util.position;

import me.fahien.ds.util.composition.Entry;

/** BinarySearchTree Entry
 * @author Fahien */
public class BSTEntry<Key, Value> implements Entry<Key, Value> {
	private Key key;
	private Value value;
	private Position<Entry<Key, Value>> position;

	public BSTEntry(Key key, Value value, Position<Entry<Key, Value>> position) {
		this.key = key;
		this.value = value;
		this.position = position;
	}

	@Override public Key getKey() {
		return key;
	}

	@Override public Value getValue() {
		return value;
	}

	public Position<Entry<Key, Value>> getPosition() {
		return position;
	}

	@Override public String toString() {
		return "(" + key + ", " + value + ")";
	}
}
