//Node class for our graph
public class Node{
	private String name;//name of the link
	private int loc;// place in the array

	public Node(String n, int l){
		this.name = n;
		this.loc = l;
	}
	
	public String getName(){ return name;}
	public int getLoc(){ return loc;}
	
}
