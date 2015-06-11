package me.fahien.ds.map;

import me.fahien.ds.exception.InvalidKeyException;

/** AbstractMap
 * @author Fahien */
public abstract class AbstractMap<Key, Value> implements Map<Key, Value> {
	@Override public boolean isEmpty() {
		return size() == 0;
	}

	protected void checkKey(Key key) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException("Invalid key");
	}
}