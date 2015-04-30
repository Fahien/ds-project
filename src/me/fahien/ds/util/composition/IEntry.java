package me.fahien.ds.util.composition;

public interface IEntry<Key, Value> {
	public Key getKey();
	public void setKey(Key key);
	public Value getValue();
	public void setValue(Value value);
}