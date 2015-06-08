package me.fahien.ds.graph;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

import static org.testng.Assert.*;

/** Graph Test Case
 * @author Fahien */
public class GraphTest {
	private static final Logger logger = Logger.getLogger(GraphTest.class.getName());

	@Test() public void emptyTest() {
		Graph<Integer, Character> graph = new AdjacencyListGraph<>();
		assertEquals(graph.numEdges(), 0);
		assertEquals(graph.numVertices(), 0);
	}

	@Test public void vertexTest() {
		Graph<Integer, Character> graph = new AdjacencyListGraph<>();
		Vertex<Integer> one = graph.insertVertex(1);
		assertEquals(one.getElement(), (Integer) 1);
		assertEquals(graph.numVertices(), 1);
		assertEquals(graph.removeVertex(one), (Integer) 1);
		assertEquals(graph.numVertices(), 0);

	}

	@Test public void edgeTest() {
		Graph<Integer, Character> graph = new AdjacencyListGraph<>();
		Vertex<Integer> one = graph.insertVertex(1);
		Vertex<Integer> two = graph.insertVertex(2);
		assertFalse(graph.areAdjacent(one, two));

		Edge<Character> a = graph.insertEdge(one, two, 'a');
		assertEquals(a.getElement(), (Character) 'a');
		assertEquals(graph.numVertices(), 2);
		assertEquals(graph.numEdges(), 1);

		assertTrue(graph.areAdjacent(one, two));

		assertEquals(graph.removeEdge(a), (Character) 'a');
		assertEquals(graph.numEdges(), 0);
		assertEquals(graph.numVertices(), 2);

		assertFalse(graph.areAdjacent(one, two));
	}

	@Test public void oppositeTest() {
		Graph<Integer, Character> graph = new AdjacencyListGraph<>();
		Vertex<Integer> one = graph.insertVertex(1);
		Vertex<Integer> two = graph.insertVertex(2);
		Edge<Character> a = graph.insertEdge(one, two, 'a');

		assertEquals(graph.opposite(one, a), two);
	}

	@Test public void infoTest() {
		Graph<Integer, Character> graph = new AdjacencyListGraph<>();
		Vertex<Integer> one = graph.insertVertex(1);
		Vertex<Integer> two = graph.insertVertex(2);
		graph.insertEdge(one, two, 'a');

		String vertices = "Vertices of the graph\n";
		vertices += "---------------------\n";
		for (Vertex<Integer> v : graph.vertices()) {
			vertices += v.toString() + "\n";
		}
		logger.info(vertices);

		String edges = "Edges of the graph\n";
		edges += "---------------------\n";
		for (Edge<Character> e : graph.edges()) {
			edges += e.toString() + "\n";
		}
		logger.info(edges);

		for (Edge<Character> e : graph.incidentEdges(one)) {
			logger.info("Edge incidents First vertex: " + e);
		}
	}
}
