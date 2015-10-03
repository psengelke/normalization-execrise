import java.util.Iterator;
import java.util.LinkedList;

/**
 * Parses a source file and produces statistics.
 * @author Paul
 *
 */
public class JavaSourceFileParser implements SourceFileParser {
	
	public JavaSourceFileParser(){}

	@Override
	public int countCodeLines(LinkedList<String> file) {
		int count = 0;
		boolean commentOn = false;
		for (int k = 0; k < file.size(); ++k)
		{
			if (file.get(k).contains("/*") && !commentOn)
			{
				if (!file.get(k).contains("*/"))
					commentOn = true;
				else
					continue;
			}
			else if (file.get(k).contains("*/"))
			{
				commentOn = false;
			}
			else if (file.get(k).contains("//") || commentOn)
				continue;
			else
				count++;
		}
		return count;
	}

	@Override
	public int countStatements(LinkedList<String> file) {

		int count = 0;
		Iterator<String> it = file.iterator();
		while (it.hasNext()){
			
			if (it.next().contains(";")) count++;
		}
		
		return count;
	}

	@Override
	public int countClasses(LinkedList<String> file) {
		int count = 0;
		for (int k = 0; k < file.size(); ++k)
		{
			if (file.get(k).contains("class"))
			{
				count++;
			}
		}
		return count;
	}

	@Override
	public int countFunctions(LinkedList<String> file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countComments(LinkedList<String> file) {
		int count = 0;
		boolean commentOn = false;
		for (int k = 0; k < file.size(); ++k)
		{
			if (file.get(k).contains("/*") && !commentOn)
			{
				if (!file.get(k).contains("*/"))
					commentOn = true;
				else
					count++;
			}
			else if (file.get(k).contains("*/") && commentOn)
			{
				commentOn = false;
				count++;
			}
			else if (file.get(k).contains("//") && !commentOn)
				count++;
		}
		return count;
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
	public float[] calcCyclomaticComplex(LinkedList<String> file) {
		float[] complexity=new float[2];
		complexity[0]=1;
		
		int returns=0;
		
//		for (int i)
		
		return complexity;
	}

}
