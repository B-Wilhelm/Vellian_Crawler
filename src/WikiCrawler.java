import java.util.ArrayList;

/**
 * 
 * @author Brett Wilhelm
 * @author Zach Johnson
 *
 */

public class WikiCrawler {
	private String seedUrl, fileName;
	private int max;
	
	/*
	 * @param seedURL String representing relative address of the Seed URL
	 * @param max Integer primitive representing the max number of pages to crawl
	 * @param fileName String representing the name of the file that the graph will be written to
	 */
	public WikiCrawler(String seedUrl, int max, String fileName) {
		this.seedUrl = seedUrl;
		this.max = max;
		this.fileName = fileName;
	}
	
	/*
	 * @param doc String that represents the source code of an html page
	 */
	private ArrayList<String> extractLinks(String doc) {
		
		
		return null;
	}
	
	
}