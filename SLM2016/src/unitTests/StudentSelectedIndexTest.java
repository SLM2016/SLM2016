package unitTests;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mailSending.StudentSelectedIndex;

public class StudentSelectedIndexTest {
	private StudentSelectedIndex sendStudentIndex;

	@Before
	public void setUp() throws Exception {
		sendStudentIndex = new StudentSelectedIndex(0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		sendStudentIndex.add(0);
		sendStudentIndex.add(2);
		sendStudentIndex.add(6);
		StudentSelectedIndex cloneObject = sendStudentIndex.clone();
		assertEquals(cloneObject.getIndexes().get(0).intValue(), 0);
		assertEquals(cloneObject.getIndexes().get(1).intValue(), 2);
		assertEquals(cloneObject.getIndexes().get(2).intValue(), 6);
	}

	@Test
	public void testAdd() {
		sendStudentIndex.add(0);
		assertEquals(sendStudentIndex.getIndexes().get(0).intValue(), 0);
	}

	@Test
	public void testGetClassIndex() {
		assertEquals(sendStudentIndex.getClassIndex(), 0);
	}
}
