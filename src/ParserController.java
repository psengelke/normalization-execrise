import java.util.LinkedList;

/**
 * 
 * Handles the {@link JavaSourceFileParser} and {@link SourceFileReader} and interacts with the UI.
 * 
 * @author Paul
 *
 */
public class ParserController {
	
	private String makeStandardFile(String path){
		
		
		
		return null;
	}
	
	/**
	 * Generates a report of the contents of a source code file.
	 * @param path The file path of the source code file.
	 * @return A report in the form of a {@link LinkedList}.
	 */
	public LinkedList<String> parseFile(String path){
		
		String temp = makeStandardFile(path);
		LinkedList<String> file = SourceFileReader.readSourceFile(temp);
		LinkedList<String> report = new LinkedList<>();
		
		SourceFileParser parser = new JavaSourceFileParser();
		
		report.add("Number of lines of code: "+parser.countCodeLines(file));
		report.add("Number of statements: "+parser.countStatements(file));
		report.add("Number of classes: "+parser.countClasses(file));
		report.add("Number of functions: "+parser.countFunctions(file));
		report.add("Average number of statements / class: "+parser.calcAverageStatementsPerClass(file));
		report.add("Average number of statements per function: "+parser.calcAverageStatementsPerFunction(file));
		report.add("Lines of comments: "+parser.countComments(file));
		
		return report;
	}
	
	/**
	 * Entry point.
	 * @param args
	 */
	public void main(){
		
		// instantiate UI
	}
}
