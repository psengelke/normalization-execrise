import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class CSourceFileParserTester {

	@Test
	public void testCountCodeLines() {
		LinkedList<String> file=new LinkedList<>();
		file.add("public class Facts");
		file.add("{");
		file.add("// comment");
		file.add("}");
		
		CSourceFileParser parser=new CSourceFileParser();
		assertEquals(3, parser.countCodeLines(file));
	}

	@Test
	public void testCountStatements() {
		LinkedList<String> file=new LinkedList<>();
		file.add("public class Facts");
		file.add("int i;");
		file.add("i=1+1;");
		file.add("}");
		
		CSourceFileParser parser=new CSourceFileParser();
		assertEquals(2, parser.countStatements(file));
	}

	@Test
	public void testCountClasses() {
		LinkedList<String> file=new LinkedList<>();
		file.add("//public struct Facts");
		file.add("{");
		file.add("public struct facts");
		file.add("}");
		
		CSourceFileParser parser=new CSourceFileParser();
		assertEquals(1, parser.countClasses(file));
	}

	@Test
	public void testCountFunctions() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountComments() {
		LinkedList<String> file=new LinkedList<>();
		file.add("//public class Facts");
		file.add("/*");
		file.add("multi line comment");
		file.add("*/");
		
		CSourceFileParser parser=new CSourceFileParser();
		assertEquals(2, parser.countComments(file));
	}

	@Test
	public void testCalcAverageStatementsPerClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcAverageStatementsPerFunction() {
		LinkedList<String> file=new LinkedList<>();
		file.add("public class Facts");
		file.add("int i;");
		file.add("i=1+1;");
		file.add("}");
		file.add("public class Facts");
		file.add("int i;");
		file.add("i=1+1;");
		file.add("}");
		
		CSourceFileParser parser=new CSourceFileParser();
		assertEquals(2, parser.calcAverageStatementsPerClass(file));
	}

	@Test
	public void testCalcCyclomaticComplex() {
		LinkedList<String> file=new LinkedList<>();
		file.add("if this || that");
		file.add("return this");
		file.add("else return that");
		file.add("}");
		file.add("public class Facts");
		file.add("int i;");
		file.add("i=1+1;");
		file.add("}");
		
		CSourceFileParser parser=new CSourceFileParser();
		assertEquals(4, parser.calcCyclomaticComplex(file));
	}

}
