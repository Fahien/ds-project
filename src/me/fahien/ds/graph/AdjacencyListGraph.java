package me.fahien.ds.graph;

import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.position.Position;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.GraphEdge;
import me.fahien.ds.util.position.graph.GraphPosition;
import me.fahien.ds.util.position.graph.GraphVertex;
import me.fahien.ds.util.position.graph.Vertex;

/** Adjacency List representation of a Graph
 * @author Fahien */
public class AdjacencyListGraph<V, E> implements Graph<V, E> {

	private NodePositionList<Vertex<V>> vertices;
	private NodePositionList<Edge<E>> edges;

	public AdjacencyListGraph() {
		vertices = new NodePositionList<>();
		edges = new NodePositionList<>();
	}

	/** Validates the position and returns it as a GraphVertex position */
	protected GraphVertex<V> checkVertexPosition(Vertex<V> position) throws InvalidPositionException{
		if (position == null)
			throw new InvalidPositionException("Vertex position is null");
		try {
			return (GraphVertex<V>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid vertex position type");
		}
	}

	/** Validates the position and returns it as a GraphEdge position */
	protected GraphEdge<E> checkEdgePosition(Edge<E> position) throws InvalidPositionException{
		if (position == null)
			throw new InvalidPositionException("Edge position is null");
		try {
			return (GraphEdge<E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid edge position type");
		}
	}

	/** Validates the position and returns it as a GraphPosition */
	protected GraphPosition<V> checkGraphPosition(Position position) throws InvalidPositionException{
		if (position == null)
			throw new InvalidPositionException("Graph position is null");
		try {
			return (GraphPosition<V>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid graph position type");
		}
	}

	@Override public int numVertices() {
		return vertices.size();
	}

	@Override public int numEdges() {
		return edges.size();
	}

	@Override public Iterable<Vertex<V>> vertices() {
		return vertices;
	}

	@Override public Iterable<Edge<E>> edges() {
		return edges;
	}

	@Override public V replace(Vertex<V> vertexPosition, V element) throws InvalidPositionException {
		V temp = vertexPosition.getElement();
		GraphVertex<V> vertex = checkVertexPosition(vertexPosition);
		vertex.setElement(element);
		return temp;
	}

	@Override public E replace(Edge<E> edgePosition, E element) throws InvalidPositionException {
		E temp = edgePosition.getElement();
		GraphEdge<E> edge = checkEdgePosition(edgePosition);
		edge.setElement(element);
		return temp;
	}

	@Override public Iterable<Edge<E>> incidentEdges(Vertex<V> vertexPosition) throws InvalidPositionException {
		GraphVertex<V> vertex = checkVertexPosition(vertexPosition);
		PositionList<Edge<E>> incidentEdges = new NodePositionList<>();
		for (Edge<?> edge : vertex.getIncidentEdges()) {
			incidentEdges.addLast((Edge<E>) edge);
		}
		return incidentEdges;
	}

	@Override public Vertex<V>[] endVertices(Edge<E> edgePosition) throws InvalidPositionException {
		GraphEdge<E> edge = checkEdgePosition(edgePosition);
		return (Vertex<V>[]) edge.getEndVertices();
	}

	@Override public Vertex<V> opposite(Vertex<V> vertexPosition, Edge<E> edgePosition) throws InvalidPositionException {
		Vertex<V>[] endVertices = endVertices(edgePosition);
		if (endVertices[0].equals(vertexPosition)) {
			return endVertices[1];
		}
		else return endVertices[0];
	}

	@Override public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidPositionException {
		GraphVertex<V> vertexU = checkVertexPosition(u);
		GraphVertex<V> vertexV = checkVertexPosition(v);
		GraphVertex<V> vertex = (vertexU.getDegree() < vertexV.getDegree()) ? vertexU : vertexV;
		for (Edge<?> edge : vertex.getIncidentEdges()) {
			Vertex<V>[] endVertices = endVertices((Edge<E>)edge);
			if ((endVertices[0].equals(u) && endVertices[1].equals(v))
					|| (endVertices[1].equals(u) && endVertices[0].equals(v))) {
				return true;
			}
		}
		return false;
	}

	@Override public Vertex<V> insertVertex(V element) {
		GraphVertex<V> vertex = new GraphVertex<>(element);
		vertices.addLast(vertex);
		vertex.setPosition(vertices.last());
		return vertex;
	}

	@Override public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws InvalidPositionException {
		GraphVertex<V> vertexU = checkVertexPosition(u);
		GraphVertex<V> vertexV = checkVertexPosition(v);
		GraphEdge<E> edge = new GraphEdge<>(u, v, element);

		Position<Edge<?>> edgePositionU = vertexU.insertIncidence(edge);
		Position<Edge<?>> edgePositionV = vertexV.insertIncidence(edge);
		edge.setIncidences(edgePositionU, edgePositionV);

		edges.addLast(edge);
		edge.setPosition(edges.last());
		return edge;
	}

	@Override public V removeVertex(Vertex<V> vertexPosition) throws InvalidPositionException {
		GraphVertex<V> vertex = checkVertexPosition(vertexPosition);
		for (Edge<?> e : vertex.getIncidentEdges()) {
			GraphEdge<E> edge = (GraphEdge<E>) e;
			if (edge.getPosition() != null) {
				removeEdge(edge);
			}
		}
		V temp = vertex.getElement();
		vertices.remove(vertex.getPosition());
		return temp;
	}

	@Override public E removeEdge(Edge<E> edgePosition) throws InvalidPositionException {
		GraphEdge<E> edge = checkEdgePosition(edgePosition);
		E temp = edge.getElement();
		edges.remove(edge.getPosition());
		return temp;
	}
}