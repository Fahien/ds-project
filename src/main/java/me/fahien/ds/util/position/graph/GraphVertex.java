package me.fahien.ds.util.position.graph;

import java.util.Iterator;

import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.position.Position;

/** Graph vertex
 * @author Fahien */
public class GraphVertex<V, E> extends GraphPosition<V> implements Vertex<V> {
	private V element;
	private PositionList<Edge<E>> incidentEdges;
	private Position<Vertex<V>> position;

	public GraphVertex(V element) {
		this.element = element;
		incidentEdges = new NodePositionList<>();
	}

	@Override public V getElement() {
		return element;
	}

	public V setElement() {
		return element;
	}

	public int getDegree() {
		return incidentEdges.size();
	}

	public Iterable<Edge<E>> getIncidentEdges() {
		return incidentEdges;
	}

	public Position<Edge<E>> insertIncidence(Edge<E> edge) {
		incidentEdges.addLast(edge);
		return incidentEdges.last();
	}

	public void removeIncidence(Edge<E> edge) {
		Iterator<Edge<E>> iter = incidentEdges.iterator();
		while (iter.hasNext()) {
			Edge<?> e = iter.next();
			if (e.equals(edge)) {
				iter.remove();
			}
		}
	}

	public Position<Vertex<V>> getPosition() {
		return position;
	}

	public void setPosition(Position<Vertex<V>> position) {
		this.position = position;
	}

	@Override public String toString() {
		return element.toString();
	}
}