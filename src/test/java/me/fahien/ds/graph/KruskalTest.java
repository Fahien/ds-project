package me.fahien.ds.graph;


import java.util.logging.Logger;

import org.testng.annotations.Test;

import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class KruskalTest {
	private final static Logger logger = Logger.getLogger(KruskalTest.class.getName());
	
	@Test public void krustalTest() {
		Graph<Integer, Integer> graph = new AdjacencyListGraph<>();
		PositionList<Edge<Integer>> list = Kruskal.execute(graph);
		assertTrue(list.isEmpty());
		
		Vertex<Integer> a = graph.insertVertex(1);
		Vertex<Integer> b = graph.insertVertex(2);

		graph.insertEdge(a, b, 1);
		list = Kruskal.execute(graph);
		assertFalse(list.isEmpty());
		logger.info(list.toString());
	}
}