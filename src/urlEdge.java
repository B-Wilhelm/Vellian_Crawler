/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 *
 */

public class urlEdge {
	private String start, end;
	
	public urlEdge(String start, String end) {
		this.start = start;
		this.end = end;
	}
	
	public String getStart() {
		return start;
	}
	
	public String getEnd() {
		return end;
	}
	
	@Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of urlEdge or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof urlEdge)) {
            return false;
        }
         
        // typecast o to urlEdge so that we can compare data members 
        urlEdge c = (urlEdge) o;
         
        // Compare the data members and return accordingly 
        return ((c.start).equals(start) && (c.end).equals(end));
    }
}