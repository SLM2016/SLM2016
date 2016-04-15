package unitTests;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mailSending.SendStudentIndex;

public class SendStudentIndexTest {
	private SendStudentIndex sendStudentIndex;

	@Before
	public void setUp() throws Exception {
		sendStudentIndex = new SendStudentIndex();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		sendStudentIndex.Add(0);
		sendStudentIndex.Add(2);
		sendStudentIndex.Add(6);
		SendStudentIndex cloneObject = sendStudentIndex.Clone();
		assertEquals(cloneObject.GetIndexes().get(0).intValue(), 0);
		assertEquals(cloneObject.GetIndexes().get(1).intValue(), 2);
		assertEquals(cloneObject.GetIndexes().get(2).intValue(), 6);
	}

	@Test
	public void testAdd() {
		sendStudentIndex.Add(0);
		assertEquals(sendStudentIndex.GetIndexes().get(0).intValue(), 0);
	}

}
