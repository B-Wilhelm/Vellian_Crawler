import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 *
 */

public class WikiCrawler {
	static final String BASE_URL = "https://en.wikipedia.org";
	private ArrayList<String> list = new ArrayList<String>();
	private String seedUrl, fileName;
	private int max;
	private Scanner s, l;	// Scanner for entire source code, Scanner for individual links
	private String source, input, progSource;
	private static final String CONTAINS_CHECK = "/wiki/";
	private static final String[] NOT_CONTAINED = {":", "#"};
	private AdjacencyList graph; // the graph our crawler will create
	private File f;
	private Queue<String> test;	//needed for BFS change name from test
	private boolean toggle;
	
	/*
	 * @param seedURL String representing relative address of the Seed URL
	 * @param max Integer primitive representing the max number of pages to crawl
	 * @param fileName String representing the name of the file that the graph will be written to
	 */
	public WikiCrawler(String seedUrl, int max, String fileName) throws IOException {
		this.seedUrl = seedUrl;
		this.max = max;
		this.fileName = fileName;
		
		list = extractLinks(getPageSource(seedUrl));
		crawl();
	}
	
	/*
	 * @param doc String that represents the source code of an html page
	 */
	public ArrayList<String> extractLinks(String doc) {
		addToGraph(doc);
		
		return null;
	}
	
	public void crawl() {
		graph = new AdjacencyList(max);
		
		for(int i = 0; i<list.size() && list.size()<max; i++) {
			seedUrl = list.get(i).getEnd();
			temp = extractLinks(getPageSource(seedUrl));
			list.addAll(temp);
		}
	
		toggle = true;
	
		for(int i = 0; i < list.size(); i++) {
			temp = extractLinks(getPageSource(seedUrl));
			System.out.println(list.addAll(temp));
		}
	}
	
	/////////////////////////////////////////
	
	private void addToGraph(String doc) {
		graph = new AdjacencyList(max);
		ArrayList<String> newList = new ArrayList<String>();
		input = "";
		s = new Scanner(doc);	// Scanner for whole html source code
		s.useDelimiter("<p>|<P>");
		if(s.hasNext()) { s.next(); }	// Skips to just after first instance of <p> or <P>
		s.useDelimiter("href=\"|\"");
		
		while(s.hasNext()) {
			input = s.next();
			
			if((input.toLowerCase()).contains(CONTAINS_CHECK) && !((input.toLowerCase()).contains(NOT_CONTAINED[0])) && !((input.toLowerCase()).contains(NOT_CONTAINED[1])) && (input.charAt(1)=='w')) {	// Ensures properly formatted links get through
				if(!toggle && graph.size()<max) {
					
				}
				else {
					
				}
			}
		}
		s.close();
	}
	
	private String getPageSource(String urlS) {	// Takes in relative URL
		progSource = "";
		URL url;
	    InputStream input = null;
	    BufferedReader br = null;
	    String line;

	    try {
	        url = new URL(BASE_URL + urlS);
	        input = url.openStream();  // throws an IOException
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
	    } finally {
	        try {
	            if (input != null) {
	            	input.close();
	            }
	        } catch (IOException i) {
	            // all good
	        }
	    }
		return "";
	}
	
	public String getSource() {	// May make private
		return source;
	}
	
	public ArrayList<String> getList() {
		return list;
	}
	
	private void writeToFile() {
		
	}
}