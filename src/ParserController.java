import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


/**
 * 
 * Handles the {@link JavaSourceFileParser} and {@link SourceFileReader} and interacts with the UI.
 * 
 * @author Paul
 *
 */
public class ParserController {

	private enum extension {
		java, c;
	}

	private extension determineExtension(String path) {
		String[] splitPath=path.split(Pattern.quote("."));

		if (splitPath.length!=0) {
			if (splitPath[splitPath.length-1].equals("java"))
				return extension.java;
			else
				if (splitPath[splitPath.length-1].equals("c") || splitPath[splitPath.length-1].equals("h"))
					return extension.c;
		}
		return null;
	}

	private String makeStandardFile(String path){

		Random rng=new Random();
		Integer rand=rng.nextInt(500);
		String newpath=path+rand;
		/*if (determineExtension(path)==extension.java)
			command="AStyle.exe --style=java < "+path+" > "+newpath;
		else
			if (determineExtension(path)==extension.c)
				command="AStyle.exe --style=allman < "+path+" > "+newpath;
			else
				System.out.println("Unrecognized extension");*/

		StringBuffer output=new StringBuffer();
		ProcessBuilder builder = null;
		Process p;

		try {
			//				p=Runtime.getRuntime().exec(command);
			//				System.out.println(command);
			if (determineExtension(path)==extension.java)
				builder=new ProcessBuilder("AStyle.exe", "--style=java");
			else
				if (determineExtension(path)==extension.c)
					builder=new ProcessBuilder("AStyle.exe", "--style=c");
			if (builder!=null) {
				builder.redirectInput(new File(path));
				builder.redirectOutput(new File(newpath));
				p=builder.start();
				p.waitFor();

				BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line="";
				while ((line=reader.readLine())!=null)
					output.append(line+"\n");
				System.out.println(output.toString());

				return newpath;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
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
		
		SourceFileParser parser = (determineExtension(path) == extension.java)?new JavaSourceFileParser(): new CSourceFileParser();
		
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
	public static void main(String[] args){
		ParserController pc = new ParserController();
		UserInterface ui = new UserInterface(pc);
		ui.initUi();
		
	}
}
