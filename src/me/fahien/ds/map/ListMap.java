package me.fahien.ds.map;

import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.composition.IEntry;
import me.fahien.ds.util.position.Position;

public class ListMap<Key, Value> implements Map<Key, Value> {
	private PositionList<IEntry<Key, Value>> list;

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
		for(Position<IEntry<Key, Value>> position : list.getPositions()) {
			IEntry<Key, Value> entry = position.getElement();
			if (entry.getKey().equals(key)) {
				Value temp = entry.getValue();
				list.set(position, new Entry<>(key, value));
				return temp;
			}
		}
		list.addLast (new Entry<>(key, value));
		return null;
	}

	@Override
	public Value get (Key key) throws InvalidKeyException {
		checkKey(key);
		for (IEntry<Key, Value> entry : list) {
			if (entry.getKey().equals(key))
				return entry.getValue();
		}
		return null;
	}

	@Override
	public Value remove (Key key) throws InvalidKeyException {
		checkKey(key);
		for(Position<IEntry<Key, Value>> position : list.getPositions()) {
			IEntry<Key, Value> entry = position.getElement();
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
			for (Position<IEntry<Key, Value>> current = list.first(); current != null; current = list.next(current)) {
				iterable.addLast(current.getElement().getKey());
			}
		}
		return iterable;
	}

	@Override
	public Iterable<Value> values () {
		PositionList<Value> iterable = new NodePositionList<>();
		if (!list.isEmpty()) {
			for (Position<IEntry<Key, Value>> current = list.first(); current != null; current = list.next(current)) {
				iterable.addLast(current.getElement().getValue());
			}
		}
		return iterable;
	}

	@Override
	public Iterable<IEntry<Key, Value>> entries () {
		PositionList<IEntry<Key, Value>> iterable = new NodePositionList<>();
		if (!list.isEmpty()) {
			for (Position<IEntry<Key, Value>> current = list.first(); current != null; current = list.next(current)) {
				iterable.addLast(current.getElement());
			}
		}
		return iterable;
	}
}