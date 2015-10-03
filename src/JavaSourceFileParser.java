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

		int count = 0;
		Iterator<String> it = file.iterator();
		while (it.hasNext()){
			
			String line = it.next();
			if (line.matches("((?:private)|(?:public)|(?:protected))(?:static)(?:final)\\s[_a-zA-z][_a-zA-Z0-9]*\\s\\(([_a-zA-z][_a-zA-Z0-9]*\\s[_a-zA-z][_a-zA-Z0-9]*)[,\\s[_a-zA-z][_a-zA-Z0-9]*]\\)\\s{\\s")) //parameters
				count++;
		}
		
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
		int methods=countFunctions(file);
		complexity[0]=methods; //1 for each method
		
		int returns=0, selection=0, loop=0, operators=0, exceptions=0;
		
		for (int i=0; i<file.size(); i++) {
			if (file.get(i).contains("return"))
				returns++;
			if (file.get(i).contains("if") || file.get(i).contains("else") || file.get(i).contains("case") || file.get(i).contains("default"))
				selection++;
			if (file.get(i).contains("for") || file.get(i).contains("while") || file.get(i).contains("do-while") || file.get(i).contains("break") || file.get(i).contains("continue"))
				loop++;
			if (file.get(i).contains("&&") || file.get(i).contains("||") || file.get(i).contains("?") || file.get(i).contains(":"))
				operators++;
			if (file.get(i).contains("catch") || file.get(i).contains("finally") || file.get(i).contains("throw") || file.get(i).contains("throws"))
				exceptions++;
		}
		
		complexity[0]+=returns;
		complexity[0]-=methods; //subtract each last return in method
		complexity[0]+=selection+loop+operators+exceptions;
		complexity[1]=complexity[0]/methods;
		
		return complexity;
	}

}
