package me.fahien.ds.set;

import java.util.Comparator;
import java.util.logging.Logger;

import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.set.genericmerge.GenericMerge;
import me.fahien.ds.set.genericmerge.Intersect;
import me.fahien.ds.set.genericmerge.Subtract;
import me.fahien.ds.set.genericmerge.Union;
import me.fahien.ds.util.comparator.DefaultComparator;
import me.fahien.ds.util.position.Position;

/** OrderedListSet
 * @author Fahien */
public class OrderedListSet<E> implements Set<E> {
	private static final Logger logger = Logger.getLogger(OrderedListSet.class.getName());

	private Comparator<E> comparator;
	private NodePositionList<E> list = new NodePositionList<>();
	private Position<Set<E>> position;

	public OrderedListSet() {
		comparator = new DefaultComparator<>();
	}

	public OrderedListSet(NodePositionList<E> list) {
		comparator = new DefaultComparator<>();
		this.list = list;
	}

	public Position<E> first() {
		return list.first();
	}

	public E remove(Position<E> position) {
		return list.remove(position);
	}

	public PositionList<E> getList() {
		return list;
	}

	@Override public int size() {
		return list.size();
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public Set<E> union(Set<E> set) {
		GenericMerge<E> union = new Union<>(comparator);
		try {
			list = union.genericMerge(list, ((OrderedListSet<E>)set).list.clone());
		} catch (ClassCastException e) {
			throw new ClassCastException("The set is not the same type");
		}
		return this;
	}

	@Override public Set<E> intersect(Set<E> set) {
		GenericMerge<E> intersect = new Intersect<>(comparator);
		NodePositionList<E> temp;
		try {
			temp = intersect.genericMerge(list.clone(), ((OrderedListSet<E>) set).list.clone());
		} catch (ClassCastException e) {
			throw new ClassCastException("The set is not the same type");
		}
		return new OrderedListSet<>(temp);
	}

	@Override public Set<E> subtract(Set<E> set) {
		GenericMerge<E> subtract = new Subtract<>(comparator);
		NodePositionList<E> temp;
		try {
			temp = subtract.genericMerge(list.clone(), ((OrderedListSet<E>)set).list.clone());
		} catch (ClassCastException e) {
			throw new ClassCastException("The set is not the same type");
		}
		return new OrderedListSet<>(temp);
	}

	@Override public E fastInsert(E element) {
		list.addLast(element);
		return element;
	}

	@Override public Set<E> fastUnion(Set<E> b) {
		return union(b);
	}

	@Override public Position<Set<E>> getPosition() {
		return position;
	}

	@Override public void setPosition(Position<Set<E>> position) {
		this.position = position;
	}

	@Override public String toString() {
		return list.toString();
	}

	@Override public OrderedListSet<E> clone() {
		try {
			super.clone();
		} catch (CloneNotSupportedException e) {
			if (e.getMessage() != null && !e.getMessage().equals(OrderedListSet.class.getName())) {
				logger.warning(e.getMessage());
			}
		}
		NodePositionList<E> temp = new NodePositionList<>();
		for (Position<E> position : list.positions()) {
			temp.addLast(position.getElement());
		}
		return new OrderedListSet<>(temp);
	}
}