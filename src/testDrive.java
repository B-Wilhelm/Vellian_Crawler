import java.io.IOException;

public class testDrive {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		final String COMPLEXITY = "/wiki/Complexity_theory";
		final String CHAOS = "/wiki/Chaos_theory";
		WikiCrawler wc = new WikiCrawler(COMPLEXITY, 20, "test.txt");
		
//		for(String s: wc.addToGraph(wc.getPageSource("/wiki/Complexity_theory"))) {
//			System.out.println(s);
//		};
		
		wc.crawl();
		
		System.out.println(wc.graph.getNeighbors(COMPLEXITY));
		System.out.println("Size: " + wc.graph.getNeighbors(COMPLEXITY).size());
		
//		AdjacencyList wc.graph = wc.getList();
		
//		for(int i = 0; i < wc.graph.getMap().size(); i++) {
//			System.out.println();
//		}
		
//		System.out.println(wc.graph.getNeighbors("/wiki/Complexity_theory"));
		
//		for(int j = 0; j < wc.graph.getNeighbors(CHAOS).size(); j++) {
//			if(j < wc.graph.getNeighbors(CHAOS).size()-1) {
//				System.out.println(wc.graph.getNeighbors(CHAOS).get(j));
//			}
//			else {
//				System.out.print(wc.graph.getNeighbors(CHAOS).get(j));
//			}
//		}
	}
}