import java.util.ArrayList;

/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 *
 */

public class GraphProcessor {
	private int V;
	private AdjacencyList graph;
	private boolean visited[];
	
	public GraphProcessor(String graphData) {
		graph = new AdjacencyList(graphData);
	}
	
	public int outDegree(String v) {
		return 0;
	}
	
	public boolean sameComponent(String u, String v) {
		return true;
	}
	
	public ArrayList<String> componentVerticies(String v) {
		return null;
	}
	
	public int largestComponent() {
		return 0;
	}
	
	public int numComponents() {
		return 0;
	}
	
	public ArrayList<String> bfsPath(String u, String v) {
		ArrayList<String> path = new ArrayList<String>();
		//int u = 
		
		return path;
	}
	
	//any private helper methods
	private void DFS(int v) {
		visited = new boolean[V];
		
		DFSHelper(v, visited);
	}
	
	private void DFSHelper(int v, boolean visited[]) {
		
	}
	
	
}



