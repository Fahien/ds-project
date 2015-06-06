package me.fahien.ds.util.position.graph;

import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.position.Position;

/** Graph vertex
 * @author Fahien */
public class GraphVertex<V> extends GraphPosition<V> implements Vertex<V> {
	private V element;
	private PositionList<Edge<?>> incidentEdges;
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

	public Iterable<Edge<?>> getIncidentEdges() {
		return incidentEdges;
	}

	public Position<Edge<?>> insertIncidence(Edge<?> edge) {
		incidentEdges.addLast(edge);
		return incidentEdges.last();
	}

	public void removeIncidence(Position<Edge<?>> position) {
		incidentEdges.remove(position);
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