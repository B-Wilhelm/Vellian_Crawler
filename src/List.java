import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 *
 */

public class List<Item> implements Iterable<Item>{
	private edgeNode<Item> head;
	private int n;
	
	private static class edgeNode<Item> {
		private Item item;
		private edgeNode<Item> next;
	}
	
	public List() {
		head = null;
		n = 0;
	}
	
	public int size() {
		return n;
	}
	
	public void add(Item item){
		 edgeNode<Item> lastNode = head;
		 head = new edgeNode<Item>();
		 head.item = item;
		 head.next = lastNode;
		 n++;
	}
	
	public Iterator<Item> iterator()  {
        return new ListIterator<Item>(head);  
    }
	
	  private class ListIterator<Item> implements Iterator<Item> {
	        private edgeNode<Item> cur;

	        public ListIterator(edgeNode<Item> first) { cur = first; }

	        public boolean hasNext()  { return cur != null; }

	        public Item next() {
	            if (!hasNext()) { throw new NoSuchElementException(); }
	            Item item = cur.item;
	            cur = cur.next; 
	            return item;
	        }
	    }
}
