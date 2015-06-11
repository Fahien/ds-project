package me.fahien.ds.util.position.graph;

import me.fahien.ds.util.position.Position;

/** Graph Edge
 * @author Fahien */
public class GraphEdge<V, E> extends GraphPosition<E> implements Edge<E> {
	private E element;
	private Position<Edge<E>>[] incidentPositions;
	private Position<Edge<E>> position;
	private GraphVertex<V, E>[] endVertices;

	@SuppressWarnings("unchecked")
	public GraphEdge(Vertex<?> u, Vertex<?> v, E element) {
		this.element = element;
		endVertices = new GraphVertex[2];
		endVertices[0] = (GraphVertex<V, E>) u;
		endVertices[1] = (GraphVertex<V, E>) v;
		incidentPositions = (Position<Edge<E>>[]) new Position[2];
	}

	@Override public E getElement() {
		return element;
	}

	public GraphVertex<V, E>[] getEndVertices() {
		return endVertices;
	}

	public void setIncidences(Position<Edge<E>> e, Position<Edge<E>> f) {
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