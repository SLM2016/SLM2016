package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mailSending.ClassInfomation;

public class ClassInfomationTest {
	private ClassInfomation classInfomation;

	@Before
	public void setUp() throws Exception {
		classInfomation = new ClassInfomation("Scurm敏捷發法實作班");
		classInfomation.getStudentsInfomation().generateFakeDataThree();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		ClassInfomation cloneObject = classInfomation.clone();
		assertEquals("Scurm敏捷發法實作班", cloneObject.getClassName());
		assertEquals("Alice", cloneObject.getStudentsInfomation().getStudents().get(0));
		assertEquals("Bob", cloneObject.getStudentsInfomation().getStudents().get(1));
	}

	@Test
	public void testGetStudentsInfomation() {
		
		assertEquals("Alice", classInfomation.getStudentsInfomation().getStudents().get(0));
	}

	@Test
	public void testGetClassName() {
		assertEquals("Scurm敏捷發法實作班", classInfomation.getClassName());
	}

}
