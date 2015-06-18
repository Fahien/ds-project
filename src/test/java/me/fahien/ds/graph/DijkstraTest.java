package me.fahien.ds.graph;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import me.fahien.ds.map.Map;
import me.fahien.ds.util.position.graph.Vertex;

/** Dijkstra Test Case
 * @author Fahien */
public class DijkstraTest {
	private static final Logger logger = Logger.getLogger(DFSTest.class.getName());

	public void dijkstraTest() {
		Graph<Character, Integer> graph = new AdjacencyListGraph<>();
		Map<Vertex<Character>, Integer> map = Dijkstra.execute(graph, null);
		assertTrue(map.isEmpty());

		Vertex<Character> a = graph.insertVertex('a');
		Vertex<Character> b = graph.insertVertex('b');
		Vertex<Character> c = graph.insertVertex('c');

		graph.insertEdge(a, b, 1);
		graph.insertEdge(a, c, 2);
		graph.insertEdge(b, c, 3);
		map = Dijkstra.execute(graph, a);
		assertFalse(map.isEmpty());
		logger.info(map.entries().toString());

		map = Dijkstra.execute(graph, b);
		assertFalse(map.isEmpty());
		logger.info(map.entries().toString());

		map = Dijkstra.execute(graph, c);
		assertFalse(map.isEmpty());
		logger.info(map.entries().toString());
	}
}