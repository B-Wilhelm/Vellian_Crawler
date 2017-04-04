import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 *
 */

public class AdjacencyList {
	private Map< String, LinkedList<String>> adj;
	private final int V;
	public AdjacencyList(int v) {
		adj = new HashMap<String, LinkedList<String>>();
		V = v;
	}
	
	public AdjacencyList(String filename) {
		//TODO
		V = 0;
	}
	public void addNode(String node) {
		 adj.putIfAbsent(node, new LinkedList<String>());
	}
	
	public void addEdge(String from, String to) {
		adj.get(from).add(to);	
	}
	
	public int getOutDegree(String node)
	{
		return adj.get(node).size();
	}
	public int getMaxVertices() {
		return V;
	}
	
}
