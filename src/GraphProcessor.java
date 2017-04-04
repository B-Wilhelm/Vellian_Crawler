import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
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
	private LinkedList<String> neighbors;
	private ArrayList<String> path = new ArrayList<String>();
	private Stack<String> s = new Stack<String>();
	private Iterator<String> it;
	
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
		path = new ArrayList<String>();
		s = new Stack<String>();
		
		BFSPathFinder(u,v);
		
		while(!s.isEmpty()){
			path.add(s.pop());
		}
		return path;
	}
	//private helper methods
	private void BFSPathFinder(String u, String v) {
		setIsTraveled(u);
		s.push(u);
		if(u == v) {
			return;
		}
		neighbors = graph.getNeighbors(u);
		it = neighbors.iterator();
		while(it.hasNext()) {
			String cur = it.next();
			if (!isTraveled.get(cur)) {
				setIsTraveled(cur);
				s.push(cur);
				BFSPathFinder(cur, v);
				s.pop();
			}
		}
		s.pop();
		if(s.isEmpty()){
			return;
		}	
	}
	
	private void setIsTraveled(String v)
	{
		isTraveled.replace(v, true);
	}
	
}



