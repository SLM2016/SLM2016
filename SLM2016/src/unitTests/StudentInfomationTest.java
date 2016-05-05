package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mailSending.StudentInfomation;

public class StudentInfomationTest {
	private StudentInfomation studentInfomation;

	@Before
	public void setUp() throws Exception {
		studentInfomation = new StudentInfomation();
		studentInfomation.generateFakeDataThree();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		StudentInfomation cloneObject = studentInfomation.clone();
		assertEquals("Alice", cloneObject.getStudents().get(0));
		assertEquals("Bob", cloneObject.getStudents().get(1));
		assertEquals("Chris", cloneObject.getStudents().get(2));
		assertEquals("David", cloneObject.getStudents().get(3));
		assertEquals("Edison", cloneObject.getStudents().get(4));
		assertEquals("Frank", cloneObject.getStudents().get(5));
		assertEquals("George", cloneObject.getStudents().get(6));
		assertEquals("Harry", cloneObject.getStudents().get(7));
		assertEquals("Ivan", cloneObject.getStudents().get(8));
		assertEquals("Jack", cloneObject.getStudents().get(9));

		assertEquals("t104598007+aaa@ntut.org.tw", cloneObject.getMailAddress().get(0));
		assertEquals("t104598007+bbb@ntut.org.tw", cloneObject.getMailAddress().get(1));
		assertEquals("t104598007+ccc@ntut.org.tw", cloneObject.getMailAddress().get(2));
		assertEquals("t104598007+ddd@ntut.org.tw", cloneObject.getMailAddress().get(3));
		assertEquals("t104598007+eee@ntut.org.tw", cloneObject.getMailAddress().get(4));
		assertEquals("t104598007+fff@ntut.org.tw", cloneObject.getMailAddress().get(5));
		assertEquals("t104598007+ggg@ntut.org.tw", cloneObject.getMailAddress().get(6));
		assertEquals("t104598007+hhh@ntut.org.tw", cloneObject.getMailAddress().get(7));
		assertEquals("t104598007+iii@ntut.org.tw", cloneObject.getMailAddress().get(8));
		assertEquals("t104598007+jjj@ntut.org.tw", cloneObject.getMailAddress().get(9));
	}

	@Test
	public void testSetStudents() {
		// wait for DB implement
		studentInfomation.setStudents();
	}

	@Test
	public void testSetMailAddress() {
		// wait for DB implement
		studentInfomation.setMailAddress();
	}

}
