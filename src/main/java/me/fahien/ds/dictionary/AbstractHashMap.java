package me.fahien.ds.dictionary;

import java.util.Random;

import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.composition.Entry;

/** AbstractHashMap
 * @author Fahien */
public abstract class AbstractHashMap<Key, Value> implements Dictionary<Key, Value> {
	private static final float LOAD_FACTOR = 0.9f;

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

	/** The load factor */
	private float load;

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
		load = LOAD_FACTOR;
		createTable();
	}

	/** Updates load factor */
	protected void setLoadFactor(float load) {
		this.load = load;
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
			insert(entry.getKey(), entry.getValue());
		}
	}

	@Override public int size() {
		return size;
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public Entry<Key, Value> find(Key key) {
		return bucketFind(hashValue(key), key);
	}

	@Override public Entry<Key, Value> insert(Key key, Value value) {
		Entry<Key, Value> temp = bucketInsert(hashValue(key), key, value);
		if (size > capacity * load) {
			resize(2 * capacity - 1);
		}
		return temp;
	}

	@Override public Entry<Key, Value> remove(Entry<Key, Value> entry) {
		return bucketRemove(hashValue(entry.getKey()), entry.getKey());
	}

	/** This method should create an initially empty table
	 *  having size equal to a designated capacity instance variable */
	protected abstract void createTable();

	/** This method should mimic the semantics of the public get method */
	protected abstract Entry<Key, Value> bucketFind(int hash, Key key);

	/** This method should mimic the semantics of the public put method */
	protected abstract Entry<Key, Value> bucketInsert(int hash, Key key, Value value);

	/** This method should mimic the semantics of the public remove method */
	protected abstract Entry<Key, Value> bucketRemove(int hash, Key key);
}