package me.fahien.ds.map.hashmap;

import java.util.Random;

import me.fahien.ds.map.AbstractMap;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.composition.Entry;

/** AbstractHashMap
 * @author Fahien */
public abstract class AbstractHashMap<Key, Value> extends AbstractMap<Key, Value> {
	/** The number of entries in the dictionary */
	protected int size = 0;

	/** Length of the table */
	protected int capacity;

	/** Prime factor */
	private int prime;

	/** The scaling factor */
	private long scale;

	/** The shift factor */
	private long shift;

	/** Constructs the HashMap with a default prime */
	public AbstractHashMap(int capacity) {
		this(capacity, 109345121);
	}

	/** Constructs the HashMap with a default capacity */
	public AbstractHashMap() {
		this(17);
	}

	/** Constructs the HashMap */
	public AbstractHashMap(int capacity, int prime) {
		this.capacity = capacity;
		this.prime = prime;
		Random rand = new Random();
		scale = rand.nextInt(prime - 1) + 1;
		shift = rand.nextInt(prime);
		createTable();
	}

	/** Private utility */
	private int hashValue(Key key) {
		return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
	}

	private void resize(int capacity) {
		PositionList<Entry<Key, Value>> buffer = new NodePositionList<>();
		for (Entry<Key, Value> entry : entries()) {
			buffer.addLast(entry);
		}
		this.capacity = capacity;
		createTable();
		size = 0;
		for (Entry<Key, Value> entry : buffer) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override public int size() {
		return size;
	}

	@Override public Value get(Key key) {
		return bucketGet(hashValue(key), key);
	}

	@Override public Value put(Key key, Value value) {
		Value temp = bucketPut(hashValue(key), key, value);
		if (size > capacity / 2) {
			resize(2 * capacity - 1);
		}
		return temp;
	}

	@Override public Value remove(Key key) {
		return bucketRemove(hashValue(key), key);
	}

	/** This method should create an initially empty table
	 *  having size equal to a designated capacity instance variable */
	protected abstract void createTable();

	/** This method should mimic the semantics of the public get method */
	protected abstract Value bucketGet(int hash, Key key);

	/** This method should mimic the semantics of the public put method */
	protected abstract Value bucketPut(int hash, Key key, Value value);

	/** This method should mimic the semantics of the public remove method */
	protected abstract Value bucketRemove(int hash, Key key);
}