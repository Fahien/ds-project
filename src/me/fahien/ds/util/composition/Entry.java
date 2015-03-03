package me.fahien.ds.util.composition;

public interface Entry<Key, Value> {
	public Key getKey ();
	public Value getValue ();
}