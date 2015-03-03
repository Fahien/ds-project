package me.fahien.ds.util.composition;

public class MyEntry<Key, Value> implements Entry<Key, Value> {
	protected Key key;
	protected Value value;

	public MyEntry (Key key, Value value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Key getKey () {
		return key;
	}

	@Override
	public Value getValue () {
		return value;
	}

	@Override
	public String toString () {
		return "(" + key + ", " + value + ")";
	}
}