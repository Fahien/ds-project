package me.fahien.ds.util.composition;

import me.fahien.ds.util.position.Position;

public class LocationAwareEntry<Key, Value> extends PQEntry<Key, Value> implements Entry<Key, Value> {
	private Position<Entry<Key, Value>> location;

	public LocationAwareEntry(Key key, Value value) {
		super(key, value);
	}

	public LocationAwareEntry(Key key, Value value, Position<Entry<Key,Value>> location) {
		super(key, value);
		this.location = location;
	}

	public Position<Entry<Key, Value>> getLocation() {
		return location;
	}

	public void setLocation(Position<Entry<Key, Value>> location) {
		this.location = location;
	}
}