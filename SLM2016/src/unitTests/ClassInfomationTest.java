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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		classInfomation.addStudent("Alice");
		classInfomation.addStudent("Bob");
		ClassInfomation cloneObject = classInfomation.clone();
		assertEquals("Scurm敏捷發法實作班", cloneObject.getClassName());
		assertEquals("Alice", cloneObject.getStudents().get(0));
		assertEquals("Bob", cloneObject.getStudents().get(1));
	}

	@Test
	public void testAddStudent() {
		classInfomation.addStudent("Alice");
		assertEquals("Alice", classInfomation.getStudents().get(0));
	}

	@Test
	public void testGetClassName() {
		assertEquals("Scurm敏捷發法實作班", classInfomation.getClassName());
	}

}
