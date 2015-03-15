package me.fahien.ds.util.composition;

import me.fahien.ds.util.position.Position;

public class LocationAwareEntry<Key, Value> extends Entry<Key, Value> implements IEntry<Key, Value> {

	protected Position<IEntry<Key, Value>> location;

	public LocationAwareEntry (Key key, Value value) {
		super(key, value);
	}

	public LocationAwareEntry (Key key, Value value, Position<IEntry<Key,Value>> location) {
		super(key, value);
		this.location = location;
	}

	public Position<IEntry<Key, Value>> getLocation () {
		return location;
	}

	public void setLocation(Position<IEntry<Key, Value>> location) {
		this.location = location;
	}
}