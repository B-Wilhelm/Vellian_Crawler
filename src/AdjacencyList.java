import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 *
 */

public class AdjacencyList {
	private Map<String, LinkedList<String>> adj;//the graph itself
	private final int V;//max number of vertices
	
	@SuppressWarnings("unused")
	/**
	 * Default Constructor unused
	 * @throws FileNotFoundException
	 */
	private AdjacencyList() throws FileNotFoundException {
		V = 0;
	}
	/**
	 * Creates a graph with max number of vertices v
	 * @param v integer representing the max number of vertices
	 */
	public AdjacencyList(int v) {
		adj = new HashMap<String, LinkedList<String>>();
		V = v;
	}
	/**
	 * constructs a graph from a file
	 * @param filepath String representing the absolute file path of a graph
	 */
	public AdjacencyList(String filepath) throws FileNotFoundException {
		adj = new HashMap<String, LinkedList<String>>();
		File f = new File(filepath);
		Scanner s = new Scanner(f);
		String l1, n1, n2;
		V = s.nextInt();//first line of the file contains the number of vertices
		s.nextLine();
		//each line holds a pair of vertices
		while(s.hasNextLine()) {
			l1 = s.nextLine();
			Scanner s2 = new Scanner(l1); 
			n1 = s2.next();//vertex starting point with an edge pointing to n2
			n2 = s2.next();//second vertex
			addNode(n1);
			addNode(n2);
			addEdge(n1, n2);
			s2.close();
		}
		s.close();
	}
	/**
	 * 
	 * @param node String representing the vertex to add
	 * @return true if vertex was new and the add was sucessful
	 */
	public boolean addNode(String node) {
		 if(adj.putIfAbsent(node, new LinkedList<String>()) == null) { return true; }
		 else { return false; }
	}
	
	/**
	 * 
	 * @param from String representing the starting vertex of the edge
	 * @param to String representing the vertex at the end of the edge
	 */
	public void addEdge(String from, String to) {
		adj.get(from).add(to);	
	}
	
	/**
	 * 
	 * @param node the vertex in the graph it is starting from
	 * @return returns a linked list containing all vertices node points to
	 */
	public LinkedList<String> getNeighbors(String node) { return adj.get(node); }
	
	/**
	 * 
	 * @param node the vertex in the graph we want the outdegree of
	 * @return an int representing the number of edges that start at the sent in vertex
	 */
	public int getOutDegree(String node) { return adj.get(node).size(); }
	
	/**
	 * 
	 * @return returns the max number of vertices in the graph
	 */
	public int getMaxVertices() { return V; }
	
	/**
	 * 
	 * @return a set of all String Vertices
	 */
	public Set<String> getKeys() { return adj.keySet(); }
	
	
}
