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
	public Map< String, LinkedList<String>> adj;
	private final int V;
	
	public AdjacencyList(int v) {
		adj = new HashMap<String, LinkedList<String>>();
		V = v;
	}
	
	public AdjacencyList(String filename) throws FileNotFoundException {
		File f = new File(filename);
		Scanner s = new Scanner(f);
		String l1, n1, n2;
		V = s.nextInt();
		while(s.hasNextLine()) {
			l1 = s.nextLine();
			Scanner s2 = new Scanner(l1); 
			n1 = s2.next();
			n2 = s2.next();
			addNode(n1);
			addNode(n2);
			addEdge(n1, n2);
			s2.close();
		}
		s.close();
	}
	
	public void addNode(String node) {
		 adj.putIfAbsent(node, new LinkedList<String>());
	}
	
	public void addEdge(String from, String to) {
		adj.get(from).add(to);	
	}
	
	public LinkedList<String> getNeighbors(String node) { return adj.get(node); }
	public int getOutDegree(String node) { return adj.get(node).size(); }
	public int getMaxVertices() { return V; }
	public Set<String> getKeys() { return adj.keySet(); }
	
	
}
