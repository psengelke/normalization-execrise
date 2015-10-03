import java.util.LinkedList;

/**
 * Parses a source file and produces statistics.
 * @author Paul
 *
 */
public class SourceFileParser {
	
	private SourceFileParser(){}

	public static int countCodeLines(LinkedList<String> file){
		int count = 0;
		boolean commentOn = false;
		for (int k = 0; k < file.size(); ++k)
		{
			if (file.get(k).contains("//") || commentOn)
				continue;
			else if (file.get(k).contains("/*"))
			{
				commentOn = true;
				continue;
			}
			else if (file.get(k).contains("*/"))
			{
				commentOn = false;
				continue;
			}
			else
				count++;
		}
		return count;
	}
	
	public static int countStatements(LinkedList<String> file){
		
		return 0;
	}
	
	public static int countClasses(LinkedList<String> file){
		
		return 0;
	}
	
	public static int countFunctions(LinkedList<String> file){
		
		return 0;
	}
	
	public static int countComments(LinkedList<String> file){
		
		return 0;
	}
	
	public static int calcAverageStatementsPerClass(LinkedList<String> file){
		
		return 0;
	}
	
	public static int calcAverageStatementsPerFunction(LinkedList<String> file){
		
		return 0;
	}
}
