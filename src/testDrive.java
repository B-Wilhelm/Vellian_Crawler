import java.io.IOException;
import java.util.ArrayList;

public class testDrive {

	public static void main(String[] args) throws IOException {
		WikiCrawler wc = new WikiCrawler("/wiki/Complexity_theory", 4, "test.txt");
		
		AdjacencyList temp = wc.getList();
		
		System.out.println(temp.getKeys());
		
//		for(int i = 0; i < temp.getMap().size(); i++) {
//			System.out.println();
//		}
		
		
	}
}