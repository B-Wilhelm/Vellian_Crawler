import java.io.IOException;
import java.util.ArrayList;

public class testDrive {

	public static void main(String[] args) throws IOException {
		WikiCrawler wc = new WikiCrawler("/wiki/Complexity_theory", 4, "test.txt");
		
		ArrayList<String> temp = wc.getList();
		
//		for(int i = 0; i < temp.size(); i++) {
//			System.out.println();
//		}
		
		
	}
}