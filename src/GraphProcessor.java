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
	private Map<String, Boolean> isTraveled, undiscovered;
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
		undiscovered.putAll(isTraveled);
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
		isTraveled.clear();
		isTraveled.putAll(undiscovered);
		
		BFSPathFinder(u,v);
		
		while(!s.isEmpty()){
			path.add(s.pop());
		}
		return path;
	}
	//private helper methods used
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
	private AdjacencyList getTranspose() {
		AdjacencyList tmp = new AdjacencyList(V);
		
		for(String key : graph.getKeys()){
			neighbors = graph.getNeighbors(key);
			it = neighbors.iterator();
			while(it.hasNext()) {
				tmp.adj.get(key).add(it.next());
			}
		}
		
		return tmp;
	}
	
	private void fillOrder(String v, Map<String, Boolean> visited, Stack<String> s) {
		setIsTraveled(v);
		
		neighbors = graph.getNeighbors(v);
		it = neighbors.iterator();
		while(it.hasNext()) {
			String cur = it.next();
			if(visited.get(cur) == false) {
				fillOrder(cur, visited, s);
			}
		}
		
		s.push(new String(v));
	}
	
	
	private void setIsTraveled(String v)
	{
		isTraveled.replace(v, true);
	}
	
	private void computeSCCs() {
		
		isTraveled.clear();
		isTraveled.putAll(undiscovered);
		Stack<String> s1 = new Stack<String>();
		
		isTraveled.clear();
		isTraveled.putAll(undiscovered);
		
		for(String key : graph.getKeys()){
			if(isTraveled.get(key) == false) {
				fillOrder(key, isTraveled, s1);
			}
		}
		
		AdjacencyList reverse = getTranspose();
		
		isTraveled.clear();
		isTraveled.putAll(undiscovered);
		
		while(s1.isEmpty() == false) {
			String v = s1.pop();
			
			//TODO
		}
		
	}
	
}



