import java.util.LinkedList;

/**
 * 
 * Handles the {@link SourceFileParser} and {@link SourceFileReader} and interacts with the UI.
 * 
 * @author Paul
 *
 */
public class ParserController {
	
	private String makeStandardFile(String path){
		
		
		
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
