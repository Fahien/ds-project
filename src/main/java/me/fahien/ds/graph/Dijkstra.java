package me.fahien.ds.graph;

import me.fahien.ds.adaptablepriorityqueue.AdaptablePriorityQueue;
import me.fahien.ds.adaptablepriorityqueue.HeapAdaptablePriorityQueue;
import me.fahien.ds.map.HashTableMap;
import me.fahien.ds.map.Map;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

/** The Dijkstra's algorithm applies the greedy method to the single-source, shortest-path problem.
 * The main idea is to perform a weighted breadth-first search starting a the source vertex.
 * This algorithm iteratively grows a cloud of vertices out of the source, with the vertices
 * entering the cloud in order of their distances from the source. Thus, in each iteration,
 * the next vertex chosen is the vertex outside the cloud that is closest to the source.
 * The algorithm terminates when no more vertices are outside the cloud, at which point we have
 * a shortest path from the source to every vertex of the graph that is reachable from the source. 
 * @author Fahien */
public class Dijkstra {
	public static <V> Map<Vertex<V>, Integer> execute(Graph<V, Integer> graph, Vertex<V> source) {
		// d.get(v) is upper bound on distance from source to v
		Map<Vertex<V>, Integer> d = new HashTableMap<>();
		// map reachable v to its d value
		Map<Vertex<V>, Integer> cloud = new HashTableMap<>();
		// pq will have vertices as elements, with d.get(v) as key
		AdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>();
		// maps from vertex to its pq locator
		Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqTokens = new HashTableMap<>();
		
		// for each vertex v of the graph, add an entry to the priority queue
		// with the source having distance 0 and all others having infinite distance
		for (Vertex<V> v : graph.vertices()) {
			if (v == source) {
				d.put(v, 0);
			} else {
				d.put(v, Integer.MAX_VALUE);
			}
			pqTokens.put(v, pq.insert(d.get(v), v));	// save entry for future updates
		}
		// now begin adding reachable vertices to the cloud
		while (!pq.isEmpty()) {
			Entry<Integer, Vertex<V>> entry = pq.removeMin();
			int key = entry.getKey();
			Vertex<V> u = entry.getValue();
			cloud.put(u, key);	// this is actual distance to u
			pqTokens.remove(u);	// u is no longer in pq
			for (Edge<Integer> e : graph.incidentEdges(u)) {
				Vertex<V> v = graph.opposite(u, e);
				if (cloud.get(v) == null) {
					// perform relaxation step on edge (u, v)
					int weight = e.getElement();
					if (d.get(u) + weight < d.get(v)) {	// better path to v?
						d.put(v,  d.get(u) + weight);	// update the distance
						pq.replaceKey(pqTokens.get(v), d.get(v));	// update the pq entry
					}
				}
			}
		}
		return cloud;	// this only includes reachable vertices
	}
}