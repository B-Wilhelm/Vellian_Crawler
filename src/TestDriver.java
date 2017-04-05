import java.io.IOException;

public class TestDriver {
	public static void main(String[] args) throws IOException {
		final String Ames = "Ames";
		final String Minneapolis = "Minneapolis";
		final String Chicago = "Chicago";
        final String Denver = "Denver";
        final String Omaha = "Omaha";
		GraphProcessor gp = new GraphProcessor("C:/Users/Zach Johnson/git/CityMap.txt");
		
		//Vertices are Ames, Minneapolis, Chicago, Denver, Omaha
		int outdegree = gp.outDegree(Ames);
		System.out.println("Expected out degree: 2, Actual: " + outdegree);
		outdegree = gp.outDegree(Minneapolis);
		System.out.println("Expected out degree: 1, Actual: " + outdegree);

	}


}
