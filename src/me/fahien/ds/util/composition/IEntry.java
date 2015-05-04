package me.fahien.ds.util.composition;

public interface IEntry<Key, Value> {
	Key getKey();
	void setKey(Key key);
	Value getValue();
	void setValue(Value value);
}