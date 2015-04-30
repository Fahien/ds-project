package me.fahien.ds.util.composition;

public class Entry<Key, Value> implements IEntry<Key, Value> {
	protected Key key;
	protected Value value;

	public Entry(Key key, Value value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Key getKey() { return key; }

	@Override
	public void setKey(Key key) { this.key = key; }

	@Override
	public Value getValue() { return value; }

	@Override
	public void setValue(Value value) { this.value = value; }

	@Override
	public String toString() { return "(" + key + ", " + value + ")"; }
}