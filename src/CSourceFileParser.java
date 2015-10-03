import java.util.LinkedList;

public class CSourceFileParser implements SourceFileParser {

	@Override
	public int countCodeLines(LinkedList<String> file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countStatements(LinkedList<String> file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countClasses(LinkedList<String> file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countFunctions(LinkedList<String> file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countComments(LinkedList<String> file) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Calculates the average number of statements per class
	 * @param file: LinkedList object with file content
	 * @return float
	 */
	@Override
	public float calcAverageStatementsPerClass(LinkedList<String> file) {
		int noStatements = countStatements(file);
		int noClass = countClasses(file);
		return (noStatements/noClass);
	}
	
	/**
	 * Calculates the average number of statements per function
	 * @param file: LinkedList object with file content
	 * @return float
	 */
	@Override
	public float calcAverageStatementsPerFunction(LinkedList<String> file){
		int noStatements = countStatements(file);
		int noFunc = countFunctions(file);
		return (noStatements/noFunc);
	}

	@Override
	public float calcCyclometricComplex() {
		// TODO Auto-generated method stub
		return 0;
	}

}
