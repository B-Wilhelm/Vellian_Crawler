/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 *
 */
//Graph vertex nodes
public class Node{
	private final String name;//name of the link
	private final int loc;// this int represents the vertex number relative to max size (0-max)

	public Node(String n, int l) { 
		this.name = n;
		this.loc = l;
	}
	
	public String getName() { return name; }
	public int getLoc() { return loc; }
	
}
