

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
		fail("Not yet implemented");
	}

	@Test
	public void testCountCodeLines() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountStatements() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountClasses() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
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
