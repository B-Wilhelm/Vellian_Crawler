import java.io.IOException;

public class testDrive {

	public static void main(String[] args) throws IOException {
		WikiCrawler wc = new WikiCrawler("/wiki/Complexity_theory", 4, "test.txt");
		
		AdjacencyList temp = wc.getList();
		
//		for(int i = 0; i < temp.getMap().size(); i++) {
//			System.out.println();
//		}
		
//		System.out.println(temp.getNeighbors("/wiki/Complexity_theory"));
		
		for(int j = 0; j < temp.getNeighbors("/wiki/Complexity_theory").size(); j++) {
			System.out.println(temp.getNeighbors("/wiki/Complexity_theory").get(j));
		}
	}
}