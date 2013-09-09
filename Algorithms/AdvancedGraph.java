package Algorithms;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import Advanced.DisjointSet;
import Graphs.Edge;
import Graphs.Graph;
import Graphs.Vertex;

/**
 * Advanced Graph Algorithms
 * @author RDrapeau
 *
 */
public class AdvancedGraph {
	/**
	 * Random variable for algorithms.
	 */
	private static final Random r = new Random();
	
	/**
	 * Returns a Minimum Spanning Tree as a graph using Prim's algorithm.
	 * 
	 * @param g - The connected undirected acyclic graph
	 * @return The minimum spanning tree for the graph
	 */
	public static Graph minSpanTree(Graph g) {
		Graph result = new Graph();
		if (g.numberOfVertices() > 0) {
			Map<Vertex, Vertex> convert = new HashMap<Vertex, Vertex>();
			Queue<Edge> q = new PriorityQueue<Edge>();
			Vertex s = g.getVertex(r.nextInt(g.numberOfVertices()) + 1); // Numbered 1 to N
			
			convert.put(s, new Vertex(s.getID()));
			q.addAll(s.getEdges());
			result.addVertex(convert.get(s));
			
			while (result.numberOfVertices() != g.numberOfVertices()) {
				Edge e = q.remove();
				if (!convert.containsKey(e.getHead()) || !convert.containsKey(e.getTail())) { // New Vertex Discovered
					Vertex tail = e.getTail();
					Vertex head = e.getHead();
					
					update(convert, q, result, tail, head);
					
					tail = convert.get(tail); // Grab new Tail
					head = convert.get(head); // Grab new Head
					
					Edge shortest = new Edge(tail, head, e.getWeight());
					tail.addEdge(shortest);
					head.addEdge(shortest);
					result.addEdge(shortest);
				}
			}
		}
		return result;
	}
	
	/**
	 * Updates the Map, Heap, and the result so far to contain the newly discovered Vertex.
	 * 
	 * @param convert - Conversion from the old vertices to the new ones in the tree
	 * @param q - The Heap of edges
	 * @param result - The minimum spanning tree so far
	 * @param tail - The tail of the edge
	 * @param head - The head of the edge
	 */
	private static void update(Map<Vertex, Vertex> convert, Queue<Edge> q, Graph result, Vertex tail, Vertex head) {
		if (!convert.containsKey(tail)) { /// Doesn't contain tail
			q.addAll(tail.getEdges());
			convert.put(tail, new Vertex(tail.getID()));
			result.addVertex(convert.get(tail));
		} else { // Doesn't contain head
			q.addAll(head.getEdges());
			convert.put(head, new Vertex(head.getID()));
			result.addVertex(convert.get(head));
		}
	}
	
	/**
	 * Returns a mapping of each vertex to its clustered component in the graph using Single-Link
	 * clustering into k clusters.
	 * 
	 * @param g - The graph
	 * @param k - The number of desired clusters
	 * @return A mapping of each Vertex to its cluster
	 */
	public static Map<Vertex, Set<Vertex>> cluster(Graph g, int k) {
		Map<Vertex, Set<Vertex>> result = new HashMap<Vertex, Set<Vertex>>();
		DisjointSet<Vertex> components = new DisjointSet<Vertex>(g.getVertices());
		List<Edge> edges = g.getEdges();
		
		for (Vertex v : g.getVertices()) {
			Set<Vertex> cluster = new HashSet<Vertex>();
			cluster.add(v);
			result.put(v, cluster);
		}
		Collections.sort(edges);
		
		int i = 0;
		int size = g.numberOfVertices();
		while (i < edges.size() && size > k) {
			Edge e = edges.get(i++);
			if (!components.find(e.getTail(), e.getHead())) {
				components.union(e.getTail(), e.getHead());
				Set<Vertex> a = result.get(e.getTail());
				a.addAll(result.get(e.getHead()));
				result.put(e.getHead(), a);
				size--;
			}
		}
		return result;
	}
}
