package me.fahien.ds.graph;

import java.util.logging.Logger;

import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.position.Position;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.GraphEdge;
import me.fahien.ds.util.position.graph.GraphVertex;
import me.fahien.ds.util.position.graph.Vertex;

/** Adjacency List representation of a Graph
 * @author Fahien */
public class AdjacencyListGraph<V, E> implements Graph<V, E> {
	private static final Logger logger = Logger.getLogger(AdjacencyListGraph.class.getName());

	private NodePositionList<Vertex<V>> vertices;
	private NodePositionList<Edge<E>> edges;

	public AdjacencyListGraph() {
		vertices = new NodePositionList<>();
		edges = new NodePositionList<>();
	}

	/** Validates the position and returns it as a GraphVertex position */
	protected GraphVertex<V, E> checkVertexPosition(Vertex<V> position) throws InvalidPositionException{
		if (position == null)
			throw new InvalidPositionException("Vertex position is null");
		try {
			return (GraphVertex<V, E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid vertex position type");
		}
	}

	/** Validates the position and returns it as a GraphEdge position */
	protected GraphEdge<V, E> checkEdgePosition(Edge<E> position) throws InvalidPositionException{
		if (position == null)
			throw new InvalidPositionException("Edge position is null");
		try {
			return (GraphEdge<V, E>) position;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Invalid edge position type");
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
		GraphVertex<V, E> vertex = checkVertexPosition(vertexPosition);
		vertex.setElement(element);
		return temp;
	}

	@Override public E replace(Edge<E> edgePosition, E element) throws InvalidPositionException {
		E temp = edgePosition.getElement();
		GraphEdge<V, E> edge = checkEdgePosition(edgePosition);
		edge.setElement(element);
		return temp;
	}

	@Override public Iterable<Edge<E>> incidentEdges(Vertex<V> vertexPosition) throws InvalidPositionException {
		GraphVertex<V, E> vertex = checkVertexPosition(vertexPosition);
		PositionList<Edge<E>> incidentEdges = new NodePositionList<>();
		for (Edge<E> edge : vertex.getIncidentEdges()) {
			incidentEdges.addLast(edge);
		}
		return incidentEdges;
	}

	@Override public Vertex<V>[] endVertices(Edge<E> edgePosition) throws InvalidPositionException {
		GraphEdge<V, E> edge = checkEdgePosition(edgePosition);
		return edge.getEndVertices();
	}

	@Override public Vertex<V> opposite(Vertex<V> vertexPosition, Edge<E> edgePosition) throws InvalidPositionException {
		Vertex<V>[] endVertices = endVertices(edgePosition);
		if (endVertices[0].equals(vertexPosition)) {
			return endVertices[1];
		}
		else return endVertices[0];
	}

	@Override public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidPositionException {
		GraphVertex<V, E> vertexU = checkVertexPosition(u);
		GraphVertex<V, E> vertexV = checkVertexPosition(v);
		GraphVertex<V, E> vertex = (vertexU.getDegree() < vertexV.getDegree()) ? vertexU : vertexV;
		for (Edge<E> edge : vertex.getIncidentEdges()) {
			Vertex<V>[] endVertices = endVertices(edge);
			if ((endVertices[0].equals(u) && endVertices[1].equals(v))
					|| (endVertices[1].equals(u) && endVertices[0].equals(v))) {
				return true;
			}
		}
		return false;
	}

	@Override public Vertex<V> insertVertex(V element) {
		GraphVertex<V, E> vertex = new GraphVertex<>(element);
		vertices.addLast(vertex);
		vertex.setPosition(vertices.last());
		return vertex;
	}

	@Override public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws InvalidPositionException {
		GraphVertex<V, E> vertexU = checkVertexPosition(u);
		GraphVertex<V, E> vertexV = checkVertexPosition(v);
		GraphEdge<V, E> edge = new GraphEdge<>(u, v, element);

		Position<Edge<E>> edgePositionU = vertexU.insertIncidence(edge);
		Position<Edge<E>> edgePositionV = vertexV.insertIncidence(edge);
		edge.setIncidences(edgePositionU, edgePositionV);

		edges.addLast(edge);
		edge.setPosition(edges.last());
		return edge;
	}

	@Override public V removeVertex(Vertex<V> vertexPosition) throws InvalidPositionException {
		GraphVertex<V, E> vertex = checkVertexPosition(vertexPosition);
		for (Edge<E> e : vertex.getIncidentEdges()) {
			GraphEdge<V, E> edge = (GraphEdge<V, E>) e;
			if (edge.getPosition() != null) {
				removeEdge(edge);
			}
		}
		V temp = vertex.getElement();
		vertices.remove(vertex.getPosition());
		return temp;
	}

	@Override public E removeEdge(Edge<E> edgePosition) throws InvalidPositionException {
		GraphEdge<V, E> edge = checkEdgePosition(edgePosition);
		E temp = edge.getElement();
		for (Vertex<V> v : edge.getEndVertices()) {
			GraphVertex<V, E> vertex = (GraphVertex<V, E>) v;
			vertex.removeIncidence(edge);
		}
		try {
			edges.remove(edge.getPosition());
		} catch (InvalidPositionException e) {
			logger.warning("Exception removing edge: " + e.getMessage());
		}
		return temp;
	}
}