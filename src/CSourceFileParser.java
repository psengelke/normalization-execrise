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

	@Override
	public float calcAverageStatementsPerClass(LinkedList<String> file) {
		int noStatements = countStatements(file);
		int noClass = countClasses(file);
		return (noStatements/noClass);
	}

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
