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
		classInfomation.AddStudent("Alice");
		classInfomation.AddStudent("Bob");
		ClassInfomation cloneObject = classInfomation.Clone();
		assertEquals("Scurm敏捷發法實作班", cloneObject.GetClassName());
		assertEquals("Alice", cloneObject.GetStudents().get(0));
		assertEquals("Bob", cloneObject.GetStudents().get(1));
	}

	@Test
	public void testAddStudent() {
		classInfomation.AddStudent("Alice");
		assertEquals("Alice", classInfomation.GetStudents().get(0));
	}

	@Test
	public void testGetClassName() {
		assertEquals("Scurm敏捷發法實作班", classInfomation.GetClassName());
	}

}
