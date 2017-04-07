import java.io.IOException;

public class TestDrive {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		final String COMPLEXITY = "/wiki/Complexity_theory";
		final String CHAOS = "/wiki/Chaos_theory";
		final String COM_SCI = "/wiki/Computer_science";
		WikiCrawler wc = new WikiCrawler(COM_SCI, 500, "WikiCS.txt");
		
		wc.crawl();
	}
}