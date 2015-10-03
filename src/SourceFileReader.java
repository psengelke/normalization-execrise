import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class SourceFileReader {
	
	/**
	 * Opens the source file and reads the contents into a linked list
	 * @param filePath: file path of the specific file
	 * @return  LinkedList object 
	 */
	public static LinkedList<String> readSourceFile(String filePath)
	{
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
		{
		    String line;
		    LinkedList<String> list= new LinkedList<>();
		    while ((line = br.readLine()) != null) 
		    {
		       if(line.length()!=0)
		       {
		    	   list.add(line);
		       }
		    }
		    return list;
		}
		catch (Exception e) 
		{
			System.out.println("File could not be opened.");
		}
		
		return null;
		
	}
}
