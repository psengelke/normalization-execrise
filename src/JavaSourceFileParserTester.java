

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JavaSourceFileParserTester {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testJavaSourceFileParser() {
		
	}

	@Test
	public void testCountCodeLines() {

		LinkedList<String> file = new LinkedList<>();
		file.add("public class Facts {");
		file.add("// comment");
		file.add("private void makeFact(){}");
		file.add("}");
		
		JavaSourceFileParser parser = new JavaSourceFileParser();
		assertEquals(3, parser.countCodeLines(file));
	}

	@Test
	public void testCountStatements() {

		LinkedList<String> file = new LinkedList<>();
		file.add("public class Facts {");
		file.add("// comment");
		file.add("private void makeFact(){");
		file.add("int count = 0;");
		file.add("count = count + 1;");
		file.add("count = count + 1;");
		file.add("}");
		file.add("}");
		
		assertEquals(3, new JavaSourceFileParser().countStatements(file));
	}

	@Test
	public void testCountClasses() {

		LinkedList<String> file = new LinkedList<>();
		file.add("public class Facts {");
		file.add("// comment");
		file.add("private void makeFact(){");
		file.add("int count = 0;");
		file.add("count = count + 1;");
		file.add("count = count + 1;");
		file.add("}");
		file.add("private class bob {}");
		file.add("private class jess {}");
		file.add("}");
		
		assertEquals(3, new JavaSourceFileParser().countClasses(file));
	}

	@Test
	public void testCountFunctions() {
		
		LinkedList<String> file = new LinkedList<>();
		
		file.add("private RemoteProvisioningState(int value,String stringValue) {");
		
		JavaSourceFileParser parser = new JavaSourceFileParser();
		assertEquals(1, parser.countFunctions(file));
	}

	@Test
	public void testCountComments() {
		
		LinkedList<String> file = new LinkedList<>();
		file.add("public class Facts {");
		file.add("// comment");
		file.add("// comment");
		file.add("// comment");
		file.add("private void makeFact(){");
		file.add("int count = 0;");
		file.add("count = count + 1;");
		file.add("count = count + 1;");
		file.add("}");
		file.add("private class bob {}");
		file.add("private class jess {}");
		file.add("}");
		
		assertEquals(3, new JavaSourceFileParser().countComments(file));
	}

	@Test
	public void testCalcAverageStatementsPerClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcAverageStatementsPerFunction() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcCyclometricComplex() {
		fail("Not yet implemented");
	}

}
