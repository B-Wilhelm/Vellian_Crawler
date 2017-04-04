import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
	private Map<String, Boolean> isTraveled;
	
	public GraphProcessor(String graphData) {
		graph = new AdjacencyList(graphData);
		isTraveled = new HashMap<String, Boolean>();
		
		for(String key : graph.getKeys()) {
			isTraveled.put(key, false);
		}
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
	private void setIsTraveled(String v)
	{
		
	}
	
}



