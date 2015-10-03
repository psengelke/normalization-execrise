import java.util.LinkedList;

public interface SourceFileParser {

	/**
	 * Counts all the lines of code, excluding commnets.
	 * @param file The source file as a {@link LinkedList}.
	 * @return The number of lines of code.
	 */
	public int countCodeLines(LinkedList<String> file);
	
	/**
	 * Counts the number of statements in the source file.
	 * @param file The source file as a {@link LinkedList}.
	 * @return The number of statements in the code.
	 */
	public int countStatements(LinkedList<String> file);
	
	/**
	 * Count the number of classes in source file.
	 * @param file The source file as a {@link LinkedList}.
	 * @return The number of classes in the file.
	 */
	public int countClasses(LinkedList<String> file);
	
	/**
	 * Counts the number of functions in the source file.
	 * @param file The source file as a {@link LinkedList}.
	 * @return
	 */
	public int countFunctions(LinkedList<String> file);
	
	/**
	 * Counts the number of comments in the source file.
	 * @param file The source file as a {@link LinkedList}.
	 * @return The number of comments.
	 */
	public int countComments(LinkedList<String> file);

	/**
	 * Calculates the average number of statements per class.
	 * @param file: The source file as a {@link LinkedList}.
	 * @return float
	 */
	public float calcAverageStatementsPerClass(LinkedList<String> file);
	
	/**
	 * Calculates the average number of statements per function.
	 * @param file: The source file as a {@link LinkedList}.
	 * @return float
	 */
	public float calcAverageStatementsPerFunction(LinkedList<String> file);
}
