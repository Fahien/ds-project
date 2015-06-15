package me.fahien.ds.graph;

import java.util.logging.Logger;

import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.GraphVertex;
import me.fahien.ds.util.position.graph.Vertex;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/** DFS Test Case
 * @author Fahien */
public class DFSTest {
	private static final Logger logger = Logger.getLogger(DFSTest.class.getName());

	@Test public void dfsTest() {
		Graph<Integer, Character> graph = new AdjacencyListGraph<>();
		assertEquals(ComponentsDFS.execute(graph), 0);
		
		Vertex<Integer> one = new GraphVertex<Integer, Character>(1);
		Vertex<Integer> two = new GraphVertex<Integer, Character>(2);

		assertEquals(ComponentsDFS.execute(graph), 0);
		assertTrue(FindPathDFS.execute(graph, one, two).isEmpty());
		assertTrue(FindCycleDFS.execute(graph, one).isEmpty());

		one = graph.insertVertex(1);
		two = graph.insertVertex(2);
		assertEquals(ComponentsDFS.execute(graph), 2);
		assertTrue(FindPathDFS.execute(graph, one, two).isEmpty());
		assertTrue(FindCycleDFS.execute(graph, one).isEmpty());

		graph.insertEdge(one, two, 'a');
		assertEquals(ComponentsDFS.execute(graph), 1);
		assertFalse(FindPathDFS.execute(graph, one, two).isEmpty());
		assertTrue(FindCycleDFS.execute(graph, one).isEmpty());
		
		Vertex<Integer> three = graph.insertVertex(3);
		graph.insertEdge(two, three, 'b');
		assertTrue(FindCycleDFS.execute(graph, one).isEmpty());
		graph.insertEdge(three, one, 'c');
		PositionList<Edge<Character>> cycle = FindCycleDFS.execute(graph, one);
		assertFalse(cycle.isEmpty());
		String cycleString = "";
		for (Edge<Character> edge : cycle) {
			cycleString += edge.getElement() + " ";
		}
		logger.info("The cycle is: " + cycleString);
	}
}