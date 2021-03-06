import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Brett Wilhelm
 * @author Zach Johnson
 */

public class WikiCrawler {
	static final String BASE_URL = "https://en.wikipedia.org";
	private String seedUrl, fileName;
	private int max;
	private Scanner s;	// Scanner for source code
	private static final String CONTAINS_CHECK = "/wiki/";
	private static final String[] NOT_CONTAINED = {":", "#"};
	private Queue<String> queue;	//needed for BFS change name from test
	private int counter;
	private Map<String, Boolean> isTraveled;
	private ArrayList<String> dupeList, printList;
	private int requestCount = 0;
	private boolean toggleCounter;
	private AdjacencyList graph; // the graph our crawler will create
	
	/**
	 * Constructor
	 * @param seedURL String representing relative address of the Seed URL
	 * @param max Integer primitive representing the max number of pages to crawl
	 * @param fileName String representing the name of the file that the graph will be written to
	 */
	public WikiCrawler(String seedUrl, int max, String fileName) {
		this.seedUrl = seedUrl;
		this.max = max;
		this.fileName = fileName;
		
		dupeList = new ArrayList<String>();
		graph = new AdjacencyList(max);
	}
	
	/**
	 * Extracts links from graph that was pulled from a wiki page in addToGraph()
	 * @param doc String that represents the source code of an html page
	 * @return temp ArrayList filled with URLs (Strings) that were pulled from a wiki page
	 */
	public ArrayList<String> extractLinks(String doc) {
		ArrayList<String> temp = new ArrayList<String>(graph.getNeighbors(doc));
		return temp;
	}
	
	/**
	 * Initiates the bfs that fills the graph with data from all wiki pages
	 */
	public void crawl() {
		toggleCounter = false;
		counter = 0;
		bfs(seedUrl);
		writeToFile(getPrintData());
	}
	
	//////////////////////////////////////////
	
	/**
	 * Sets the value of a key in the isTraveled map to true
	 * @param v String that represents the key in the isTraveled Map
	 */
	private void setIsTraveled(String v) {
		isTraveled.replace(v, true);
	}
	
	/**
	 * Uses bfsearch to add links from wiki pages to a graph
	 * @param url String that represents the URL of the first link that you send in to the constructor
	 */
	private void bfs(String url) {
		ArrayList<String> strList;
		LinkedList<String> neighbours;
		queue = new LinkedList<String>();
		isTraveled = new HashMap<String, Boolean>();
		Iterator<String> iter;
		printList = new ArrayList<String>();
		
		graph.addNode(url);
		setIsTraveled(url);
		queue.add(url);
		
		while(queue.size() != 0) {
			String s = queue.remove();
			graph.addNode(s);
			strList = addToGraph(getPageSource(s), s);
			
			for(int i = 0; i < strList.size(); i++) {
				graph.addNode(strList.get(i));
				graph.addEdge(s, strList.get(i));
				isTraveled.putIfAbsent(strList.get(i), false);
				printList.add(s + " " + strList.get(i));
			}
			
			neighbours = graph.getNeighbors(s);
			iter = neighbours.listIterator();
			
			while(iter.hasNext()) {
				String cur = iter.next();
				
				if(!(isTraveled.get(cur))) {
					setIsTraveled(cur);
					queue.add(cur);
				}
			}
		}
	}
	
	/**
	 * Finds links in webpage source code, checks them for validity and returns them
	 * @param doc Represents the source code of a webpage
	 * @param url Represents the url given to the constructor
	 * @return The list of valid links found on a page
	 */
	private ArrayList<String> addToGraph(String doc, String url) {
		ArrayList<String> tempList = new ArrayList<String>();
		ArrayList<String> tempDupeList = new ArrayList<String>();
		String input = "";
		s = new Scanner(doc);	// Scanner for whole html source code
		s.useDelimiter("<p>|<P>");
		if(s.hasNext()) { s.next(); }	// Skips to just after first instance of <p> or <P>
		s.useDelimiter("href=\"|\"");
		
		while(s.hasNext()) {
			input = s.next();
			dupeList.add(url);
			
			if((input.toLowerCase()).contains(CONTAINS_CHECK) && !((input.toLowerCase()).contains(NOT_CONTAINED[0])) && !((input.toLowerCase()).contains(NOT_CONTAINED[1])) && (input.charAt(1)=='w')) {	// Ensures properly formatted links get through
				if(!(dupeList.contains(input))) {
					if((counter < max && !(toggleCounter))) {
						tempList.add(input);
						dupeList.add(input);
						counter++;
					}
				}
				else if(dupeList.contains(input) && !(tempDupeList.contains(input)) && counter>=max && toggleCounter && !(input.equals(url))) {
					tempList.add(input);
					tempDupeList.add(input);
				}
			}
		}
		if(counter >= max) {
			toggleCounter = true;
		}
		s.close();
		return (tempList);
	}
	
	/**
	 * Returns the source code of a webpage
	 * @param urlS Relative URL of the wiki page that needs to have its source code grabbed
	 * @return String that contains the page's full source code
	 */
	private String getPageSource(String urlS) {
		String progSource = "";
		URL url;
	    InputStream input = null;
	    BufferedReader br = null;
	    String line;

	    try {
	    	if(requestCount > 99) {
	    		requestCount = 0;
	    		TimeUnit.SECONDS.sleep(3);	// Waits for 3 seconds after every 100 requests
	    	}
	    	
	        url = new URL(BASE_URL + urlS);	        
	        input = url.openStream();  // throws an IOException
	        requestCount++;
	        br = new BufferedReader(new InputStreamReader(input));
	        if ((line = br.readLine()) != null) {
	        	progSource += line;
	        }
	        while ((line = br.readLine()) != null) {
	            progSource += "\n" + line;
	        }
	        return progSource;
	        
	    } catch (MalformedURLException m) {
	         m.printStackTrace();
	    } catch (IOException i) {
	         i.printStackTrace();
	    } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
	        try {
	            if (input != null) {
	            	input.close();
	            }
	        } catch (IOException i) {
	            // all good
	        }
	    }
		return null;
	}
	
	/**
	 * Writes the graph to a file
	 * @param data String representing the graph to be written to the file
	 */
	public void writeToFile(String data) {
		try{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    writer.print(data);
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	/**
	 * Returns String of the graph
	 * @return String representation of the graph that will eventually be written to a file
	 */
	private String getPrintData() {
		String data = max + "\n";
		
		for(int i = 0; i < printList.size(); i++) {
			data += printList.get(i);
			if(i < printList.size()-1) {
				data += "\n";
			}
		}
		
		return data;
	}
}