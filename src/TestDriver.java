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
		int outDegree = gp.outDegree(Ames);
		System.out.println("Expected out degree: 2, Actual: " + outDegree);
		outDegree = gp.outDegree(Minneapolis);
		System.out.println("Expected out degree: 1, Actual: " + outDegree);
		int biggestComponent = gp.largestComponent();
		System.out.println("Expected: 2, Actual: " + biggestComponent);
		
		int numC = gp.numComponents();
		System.out.println("Expected: 4, Actual: " + numC);
		
	}


}
