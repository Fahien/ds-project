package me.fahien.ds.util.position.graph;

import me.fahien.ds.util.position.Position;

/** Graph Edge
 * @author Fahien */
public class GraphEdge<E> extends GraphPosition<E> implements Edge<E> {
	private E element;
	private Position<Edge<?>>[] incidentPositions;
	private Position<Edge<E>> position;
	private GraphVertex<?>[] endVertices;

	public GraphEdge(Vertex<?> u, Vertex<?> v, E element) {
		this.element = element;
		endVertices = new GraphVertex[2];
		endVertices[0] = (GraphVertex<?>) u;
		endVertices[1] = (GraphVertex<?>) v;
		incidentPositions = (Position<Edge<?>>[]) new Position[2];
	}

	@Override public E getElement() {
		return element;
	}

	public GraphVertex<?>[] getEndVertices() {
		return endVertices;
	}

	public Position<Edge<?>>[] getIncidentPositions() {
		return incidentPositions;
	}

	public void setIncidences(Position<Edge<?>> e, Position<Edge<?>> f) {
		incidentPositions[0] = e;
		incidentPositions[1] = f;
	}

	public Position<Edge<E>> getPosition() {
		return position;
	}

	public void setPosition(Position<Edge<E>> position) {
		this.position = position;
	}

	@Override public String toString() {
		return element + " (" + endVertices[0] + ", " + endVertices[1] + ")";
	}
}