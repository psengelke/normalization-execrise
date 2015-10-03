import java.util.LinkedList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * Handles the {@link SourceFileParser} and {@link SourceFileReader} and interacts with the UI.
 * 
 * @author Paul
 *
 */
public class ParserController {
	
	private enum extension {
		java, c;
	}
	
	public extension determineExtension(String path) {
		String[] splitPath=path.split(".");
		if (splitPath[splitPath.length].equals("java"))
			return extension.java;
		else
			if (splitPath[splitPath.length].equals("c") || splitPath[splitPath.length].equals("h"))
				return extension.c;
		return null;
	}

	private String makeStandardFile(String path){
		
		String command="";

		Random rng=new Random();
		Integer rand=rng.nextInt();
		String newpath=path+rand;
		if (determineExtension(path)==extension.java)
			command="AStyle.exe --style=java < "+path+" > "+newpath;
		else
			if (determineExtension(path)==extension.c)
				command="AStyle.exe --style=allman < "+path+" > "+newpath;
			else
				System.out.println("Unrecognized extension");

		StringBuffer output=new StringBuffer();
		Process p;

		try {
			if (command!="") {
				p=Runtime.getRuntime().exec(command);
				p.waitFor();
				BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line="";
				while ((line=reader.readLine())!=null)
					output.append(line+"\n");
				return newpath;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public LinkedList<String> parseFile(String path){

		String temp = makeStandardFile(path);

		LinkedList<String> file = SourceFileReader.readSourceFile(temp);

		return null;
	}

	public void main(String[] args){

		// instantiate UI
	}
}
