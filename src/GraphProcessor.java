import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 *
 */

public class GraphProcessor {
	private int V;
	private AdjacencyList graph;
	
	public GraphProcessor(String graphData) {
		graph = new AdjacencyList(graphData);
	}
	
	public int outDegree(String v) {
		return graph.getOutDegree(v);
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
		Stack<String> s = new Stack<String>();
		//setup visited/unexplored list
		//setup discovery/back list
		return path;
	}
	
	//any private helper methods
	private void DFS(String v) {
		//TODO
	}
	
	private void DFSHelper(int v, boolean visited[]) {
		//TODO
	}
	
	
}



