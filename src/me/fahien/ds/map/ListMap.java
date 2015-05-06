package me.fahien.ds.map;

import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.composition.PQEntry;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.position.Position;

public class ListMap<Key, Value> implements Map<Key, Value> {
	private PositionList<Entry<Key, Value>> list;

	public ListMap () {
		list = new NodePositionList<>();
	}

	protected void checkKey (Key key) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException("Invalid key");
	}

	@Override
	public int size () {
		return list.size();
	}

	@Override
	public boolean isEmpty () {
		return list.isEmpty();
	}

	@Override
	public Value put (Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		for(Position<Entry<Key, Value>> position : list.positions()) {
			Entry<Key, Value> entry = position.getElement();
			if (entry.getKey().equals(key)) {
				Value temp = entry.getValue();
				list.set(position, new PQEntry<>(key, value));
				return temp;
			}
		}
		list.addLast (new PQEntry<>(key, value));
		return null;
	}

	@Override
	public Value get (Key key) throws InvalidKeyException {
		checkKey(key);
		for (Entry<Key, Value> entry : list) {
			if (entry.getKey().equals(key))
				return entry.getValue();
		}
		return null;
	}

	@Override
	public Value remove (Key key) throws InvalidKeyException {
		checkKey(key);
		for(Position<Entry<Key, Value>> position : list.positions()) {
			Entry<Key, Value> entry = position.getElement();
			if (entry.getKey().equals(key)) {
				Value temp = entry.getValue();
				list.remove(position);
				return temp;
			}
		}
		return null;
	}

	@Override
	public Iterable<Key> keys () {
		PositionList<Key> iterable = new NodePositionList<>();
		if (!list.isEmpty()) {
			for (Position<Entry<Key, Value>> current = list.first(); current != null; current = list.next(current)) {
				iterable.addLast(current.getElement().getKey());
			}
		}
		return iterable;
	}

	@Override
	public Iterable<Value> values () {
		PositionList<Value> iterable = new NodePositionList<>();
		if (!list.isEmpty()) {
			for (Position<Entry<Key, Value>> current = list.first(); current != null; current = list.next(current)) {
				iterable.addLast(current.getElement().getValue());
			}
		}
		return iterable;
	}

	@Override
	public Iterable<Entry<Key, Value>> entries () {
		PositionList<Entry<Key, Value>> iterable = new NodePositionList<>();
		if (!list.isEmpty()) {
			for (Position<Entry<Key, Value>> current = list.first(); current != null; current = list.next(current)) {
				iterable.addLast(current.getElement());
			}
		}
		return iterable;
	}
}