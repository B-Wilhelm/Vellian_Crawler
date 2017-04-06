import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 * 
 */

public class WikiCrawler {
	static final String BASE_URL = "https://en.wikipedia.org";
	private String seedUrl, fileName;
	private int max;
	private Scanner s;	// Scanner for source code
	private String source, progSource;
	private static final String CONTAINS_CHECK = "/wiki/";
	private static final String[] NOT_CONTAINED = {":", "#"};
	private URI f;
	private Queue<String> queue;	//needed for BFS change name from test
	private boolean counterToggle;
	private int counter;
	private Map<String, Boolean> isTraveled;
	private int requestCount = 0;
	public AdjacencyList graph; // the graph our crawler will create
	
	/*
	 * @param seedURL String representing relative address of the Seed URL
	 * @param max Integer primitive representing the max number of pages to crawl
	 * @param fileName String representing the name of the file that the graph will be written to
	 */
	public WikiCrawler(String seedUrl, int max, String fileName) throws IOException {
		this.seedUrl = seedUrl;
		this.max = max;
		this.fileName = fileName;
		counter = 0;
		
		graph = new AdjacencyList(max);
	}
	
	/*
	 * @param doc String that represents the source code of an html page
	 * @return temp ArrayList filled with URLs (Strings) that were pulled from a wiki page
	 */
	public ArrayList<String> extractLinks(String doc) {
		ArrayList<String> temp = new ArrayList<String>(graph.getNeighbors(doc));
		return temp;
	}
	
	public void crawl() {
		bfs(seedUrl);
	}
	
	/////////////////////////////s////////////
	
	private void setIsTraveled(String v) {
		isTraveled.replace(v, true);
	}
	
	private void bfs(String url) {
		ArrayList<String> strList;
		LinkedList<String> neighbours;
		queue = new LinkedList<String>();
		isTraveled = new HashMap<String, Boolean>();
		Iterator<String> iter;
		
		graph.addNode(url);
		setIsTraveled(url);
		queue.add(url);
		
		while(queue.size() != 0) {
			String s = queue.remove();
			graph.addNode(s);
			strList = addToGraph(getPageSource(s));
			
			for(int i = 0; i < strList.size(); i++) {
				graph.addNode(strList.get(i));
				graph.addEdge(s, strList.get(i));
				isTraveled.putIfAbsent(strList.get(i), false);
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
	
	public ArrayList<String> addToGraph(String doc) {
		ArrayList<String> tempList = new ArrayList<String>();
		String input = "";
		s = new Scanner(doc);	// Scanner for whole html source code
		s.useDelimiter("<p>|<P>");
		if(s.hasNext()) { s.next(); }	// Skips to just after first instance of <p> or <P>
		s.useDelimiter("href=\"|\"");
		
		while(s.hasNext()) {
			input = s.next();
			if((input.toLowerCase()).contains(CONTAINS_CHECK) && !((input.toLowerCase()).contains(NOT_CONTAINED[0])) && !((input.toLowerCase()).contains(NOT_CONTAINED[1])) && (input.charAt(1)=='w')) {	// Ensures properly formatted links get through
				if((counter < max) && !(counterToggle)) {
					tempList.add(input);
				}
			}
		}
		s.close();
		return (tempList);
	}
	
	public String getPageSource(String urlS) {	// Takes in relative URL
		progSource = "";
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
	
	public String getSource() {	// May make private
		return source;
	}
	
	public AdjacencyList getList() {
		return graph;
	}
	
	private void writeToFile() {
		List<String> lines = Arrays.asList("test");
		Path file = Paths.get(f);
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}