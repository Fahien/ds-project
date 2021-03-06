package me.fahien.ds.partition;

import me.fahien.ds.map.Map;
import me.fahien.ds.map.hashmap.ChainHashMap;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.set.OrderedListSet;
import me.fahien.ds.set.Set;

/** ListPartition
 * @author Fahien */
public class ListPartition<E> implements Partition<E> {
	private PositionList<Set<E>> partition = new NodePositionList<>();
	private Map<E, Set<E>> elements = new ChainHashMap<>();

	@Override public int size() {
		return partition.size();
	}

	@Override public boolean isEmpty() {
		return partition.isEmpty();
	}

	@Override public Set<E> makeSet(E element) {
		Set<E> set = new OrderedListSet<>();
		set.fastInsert(element);
		partition.addLast(set);
		set.setPosition(partition.last());
		elements.put(element, set);
		return set;
	}

	@Override
	public Set<E> union(Set<E> a, Set<E> b) {
		if (a.size() > b.size()) {
			a.fastUnion(b);
			partition.remove(b.getPosition());
			return a;
		} else {
			b.fastUnion(a);
			partition.remove(a.getPosition());
			return b;
		}
	}

	@Override public Set<E> find(E element) {
		return elements.get(element);
	}

	@Override public String toString() {
		return "Partition: " + partition.toString();
	}
}
