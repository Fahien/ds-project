package me.fahien.ds.util.composition;

public class PQEntry<Key, Value> implements Entry<Key, Value> {
	private Key key;
	private Value value;

	public PQEntry(Key key, Value value) {
		this.key = key;
		this.value = value;
	}

	@Override public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	@Override public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	@Override public String toString() {
		return "(" + key + ", " + value + ")";
	}
}