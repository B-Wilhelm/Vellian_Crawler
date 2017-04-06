import java.io.IOException;

public class testDrive {

	public static void main(String[] args) throws IOException {
		WikiCrawler wc = new WikiCrawler("/wiki/Complexity_theory", 20, "test.txt");
		
//		for(String s: wc.addToGraph(wc.getPageSource("/wiki/Complexity_theory"))) {
//			System.out.println(s);
//		};
		
		wc.crawl();
		
		System.out.println(wc.graph.getNeighbors("/wiki/Complexity_theory"));
		System.out.println(wc.graph.getNeighbors("/wiki/Chaos_theory"));
		
//		AdjacencyList temp = wc.getList();
		
//		for(int i = 0; i < temp.getMap().size(); i++) {
//			System.out.println();
//		}
		
//		System.out.println(temp.getNeighbors("/wiki/Complexity_theory"));
		
//		for(int j = 0; j < temp.getNeighbors("/wiki/Complexity_theory").size(); j++) {
//			if(j < temp.getNeighbors("/wiki/Complexity_theory").size()-1) {
//				System.out.println(temp.getNeighbors("/wiki/Complexity_theory").get(j));
//			}
//			else {
//				System.out.print(temp.getNeighbors("/wiki/Complexity_theory").get(j));
//			}
//		}
	}
}