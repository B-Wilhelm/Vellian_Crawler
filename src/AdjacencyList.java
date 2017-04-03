

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AdjacencyList {
	private final int V; 
	private int E;
	private List<Node>[] adj;
	private int[] indegree;
	
	public AdjacencyList(int v) {
		if(v <= 0) {throw new IllegalArgumentException();}
		
		V = v;
		this.E = 0;
		indegree = new int[V];
		adj = (List<Node>[]) new List[V];
		for(int i = 0;i < V; i++) {
			adj[i] = new List<Node>();
		}
		
	}
	
	public void addEdge(int v, Node to) {
		isVertexValid(v);
		isVertexValid(to.getLoc());
		adj[v].add(to);
		indegree[to.getLoc()]++;
		E++;
	}
	
	public int getV() { return V;}
	public int getE() { return E;}
	
	public int outdegree(int v) {
		isVertexValid(v);
		return adj[v].size();
	}
	
	private void isVertexValid(int v) {
		if (v < 0 || v>= V) throw new IllegalArgumentException();
	}
	
}
