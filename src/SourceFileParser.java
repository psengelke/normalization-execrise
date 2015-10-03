import java.util.LinkedList;

public interface SourceFileParser {

	public int countCodeLines(LinkedList<String> file);
	
	public int countStatements(LinkedList<String> file);
	
	public int countClasses(LinkedList<String> file);
	
	public int countFunctions(LinkedList<String> file);
	
	public int countComments(LinkedList<String> file);
	
	public int calcAverageStatementsPerClass(LinkedList<String> file);
	
	public int calcAverageStatementsPerFunction(LinkedList<String> file);
}
