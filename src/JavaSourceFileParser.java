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

	@Override
	public int calcAverageStatementsPerClass(LinkedList<String> file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calcAverageStatementsPerFunction(LinkedList<String> file) {
		// TODO Auto-generated method stub
		return 0;
	}

}
