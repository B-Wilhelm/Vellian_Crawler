import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
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
	private String SCC = "";
	private String DFS = "";
	
	public GraphProcessor(String graphData) throws FileNotFoundException {
		graph = new AdjacencyList(graphData);
		isTraveled = new HashMap<String, Boolean>();
		
		for(String key : graph.getKeys()) {
			isTraveled.put(key, false);
		}
		undiscovered = new HashMap<String, Boolean>();
		undiscovered.putAll(isTraveled);
	}
	
	public int outDegree(String v) {
		return graph.getOutDegree(v);
	}
	
	public boolean sameComponent(String u, String v) {
		computeSCCs();
		ArrayList<String> SCCList;
		Scanner s1 = new Scanner(SCC);
		String line;
		while(s1.hasNextLine()) {
			line = s1.nextLine();
			Scanner s2 = new Scanner(line);
			SCCList = new ArrayList<String>();
			while(s2.hasNext())
			{
				SCCList.add(s2.next());
			}
			if(SCCList.contains(u)&& SCCList.contains(v))
			{
				s1.close();
				s2.close();
				return true;
			}	
			s2.close();
		}
		s1.close();
		return false;
	}
	
	public ArrayList<String> componentVerticies(String v) {
		computeSCCs();
		ArrayList<String> SCCList = new ArrayList<String>();
		Scanner s1 = new Scanner(SCC);
		String line;
		while(s1.hasNextLine()) {
			line = s1.nextLine();
			Scanner s2 = new Scanner(line);
			SCCList = new ArrayList<String>();
			while(s2.hasNext())
			{
				SCCList.add(s2.next());
			}
			if(SCCList.contains(v))
			{
				s1.close();
				s2.close();
				return SCCList;
			}	
			s2.close();
		}
		s1.close();
		return SCCList;
	}
	
	public int largestComponent() {
		computeSCCs();
		ArrayList<String> SCCList;
		Scanner s1 = new Scanner(SCC);
		String line;
		int size = 0;
		
		while(s1.hasNextLine()) {
			line = s1.nextLine();
			Scanner s2 = new Scanner(line);
			SCCList = new ArrayList<String>();
			while(s2.hasNext())
			{
				SCCList.add(s2.next());
			}
			if(SCCList.size() >= size){
				size = SCCList.size();
			}
			s2.close();
		}
		s1.close();
		return size;
	}
	
	public int numComponents() {
		computeSCCs();
		Scanner s1 = new Scanner(SCC);
		int count = 0;
		
		while(s1.hasNextLine()){
			count++;
			s1.nextLine();
		}
		s1.close();
		return count;
	}
	
	public ArrayList<String> bfsPath(String u, String v) {
		path = new ArrayList<String>();	
		DFS(u);
		Scanner s = new Scanner(DFS);
		while(s.hasNext()) {
			String cur = s.next();
			path.add(cur);
			if(cur.equals(v))
			{
				return path;
			}
		}
		return new ArrayList<String>();
	}
	
	//private helper methods used
	private void setIsTraveled(String v)
	{
		isTraveled.replace(v, true);
	}
	
	//shortest path helper methods
	private void DFSUtil(String u, Map<String, Boolean> visited) {
		setIsTraveled(u);
		DFS += u +" ";
		
		String cur;
		neighbors = graph.getNeighbors(u);
		it = neighbors.iterator();
		while(it.hasNext()) {
			cur = it.next();
			if(visited.get(cur) == false) {
				DFSUtil(cur, visited);
			}
		}
	}
	
	private void DFS(String u) {
		isTraveled.clear();
		isTraveled.putAll(undiscovered);
		
		DFSUtil(u, isTraveled);
	}
	
	//Strongly Connected Component helper methods
	private void DFSHelper(AdjacencyList g, String v,  Map<String, Boolean> visited) {
		setIsTraveled(v);
		SCC += v + " ";
		
		String cur;
		neighbors = g.getNeighbors(v);
		it = neighbors.iterator();
		
		while(it.hasNext()) {
			
			 cur = it.next();
			 
			if(visited.get(cur) == false) {
				DFSHelper(g, cur, visited);
			}
		}
	}
	
	private AdjacencyList getTranspose() {
		AdjacencyList tmp = new AdjacencyList(V);
		
		for(String key : graph.getKeys()){
			tmp.addNode(key);
			neighbors = graph.getNeighbors(key);
			it = neighbors.iterator();
			while(it.hasNext()) {
				String s = it.next();
				tmp.addNode(s);
				tmp.addEdge(s, key);
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
		
	private void computeSCCs() {
		SCC = "";
		isTraveled.clear();
		isTraveled.putAll(undiscovered);
		Stack<String> s1 = new Stack<String>();
			
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
			
			if(isTraveled.get(v) == false)
			{
				DFSHelper(reverse, v, isTraveled);
				SCC += "\n";
			}
			
		}
		
	}
	
}



