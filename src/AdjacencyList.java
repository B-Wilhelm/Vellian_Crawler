

import java.util.NoSuchElementException;

public class AdjacencyList {
	private final int V; 
	private int E;
	private List<Node> adj;
	private int[] indegree;
	
	public AdjacencyList(int v) {
		V = v;
		this.E = 0;
		indegree = new int[V];
	}
}
