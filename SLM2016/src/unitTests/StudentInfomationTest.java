package unitTests;



import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mailSending.StudentInfomation;

public class StudentInfomationTest {
	private StudentInfomation studentInfomation;

	@Before
	public void setUp() throws Exception {
		studentInfomation = new StudentInfomation();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		studentInfomation.SetStudents();
		studentInfomation.SetMailAddress();
		StudentInfomation cloneObject = studentInfomation.Clone();
		assertEquals("Alice", cloneObject.GetStudents().get(0));
		assertEquals("Bob", cloneObject.GetStudents().get(1));
		assertEquals("Chris", cloneObject.GetStudents().get(2));
		assertEquals("David", cloneObject.GetStudents().get(3));
		assertEquals("Eva", cloneObject.GetStudents().get(4));
		assertEquals("FLT", cloneObject.GetStudents().get(5));
		assertEquals("GTA", cloneObject.GetStudents().get(6));
		assertEquals("HIA", cloneObject.GetStudents().get(7));
		assertEquals("IT", cloneObject.GetStudents().get(8));
		assertEquals("Jack", cloneObject.GetStudents().get(9));

		assertEquals("Kevin", cloneObject.GetStudents().get(10));
		assertEquals("Lee", cloneObject.GetStudents().get(11));
		assertEquals("Mo", cloneObject.GetStudents().get(12));
		assertEquals("Net", cloneObject.GetStudents().get(13));
		assertEquals("Ox", cloneObject.GetStudents().get(14));
		assertEquals("P", cloneObject.GetStudents().get(15));
		assertEquals("Q", cloneObject.GetStudents().get(16));
		assertEquals("R", cloneObject.GetStudents().get(17));
		assertEquals("S", cloneObject.GetStudents().get(18));
		assertEquals("T", cloneObject.GetStudents().get(19));

		assertEquals("U", cloneObject.GetStudents().get(20));
		assertEquals("V", cloneObject.GetStudents().get(21));
		assertEquals("W", cloneObject.GetStudents().get(22));
		assertEquals("X", cloneObject.GetStudents().get(23));
		assertEquals("Y", cloneObject.GetStudents().get(24));
		assertEquals("Z", cloneObject.GetStudents().get(25));
		assertEquals("0.0", cloneObject.GetStudents().get(26));
		assertEquals("0w0", cloneObject.GetStudents().get(27));
		assertEquals("0o0", cloneObject.GetStudents().get(28));
		assertEquals("0A0", cloneObject.GetStudents().get(29));

		assertEquals("t104598007+a@ntut.org.tw", cloneObject.GetMailAddress().get(0));
		assertEquals("t104598007+b@ntut.org.tw", cloneObject.GetMailAddress().get(1));
		assertEquals("t104598007+c@ntut.org.tw", cloneObject.GetMailAddress().get(2));
		assertEquals("t104598007+d@ntut.org.tw", cloneObject.GetMailAddress().get(3));
		assertEquals("t104598007+aaa@ntut.org.tw", cloneObject.GetMailAddress().get(4));
		assertEquals("t104598007+bbb@ntut.org.tw", cloneObject.GetMailAddress().get(5));
		assertEquals("t104598007+ccc@ntut.org.tw", cloneObject.GetMailAddress().get(6));
		assertEquals("t104598007+ddd@ntut.org.tw", cloneObject.GetMailAddress().get(7));
		assertEquals("t104598007+eee@ntut.org.tw", cloneObject.GetMailAddress().get(8));
		assertEquals("t104598007+fff@ntut.org.tw", cloneObject.GetMailAddress().get(9));

		assertEquals("t104598007+ggg@ntut.org.tw", cloneObject.GetMailAddress().get(10));
		assertEquals("t104598007+hhh@ntut.org.tw", cloneObject.GetMailAddress().get(11));
		assertEquals("t104598007+iii@ntut.org.tw", cloneObject.GetMailAddress().get(12));
		assertEquals("t104598007+jjj@ntut.org.tw", cloneObject.GetMailAddress().get(13));
		assertEquals("t104598007+kkk@ntut.org.tw", cloneObject.GetMailAddress().get(14));
		assertEquals("t104598007+lll@ntut.org.tw", cloneObject.GetMailAddress().get(15));
		assertEquals("t104598007+mmm@ntut.org.tw", cloneObject.GetMailAddress().get(16));
		assertEquals("t104598007+nnn@ntut.org.tw", cloneObject.GetMailAddress().get(17));
		assertEquals("t104598007+ooo@ntut.org.tw", cloneObject.GetMailAddress().get(18));
		assertEquals("t104598007+ppp@ntut.org.tw", cloneObject.GetMailAddress().get(19));

		assertEquals("t104598007+qqq@ntut.org.tw", cloneObject.GetMailAddress().get(20));
		assertEquals("t104598007+rrr@ntut.org.tw", cloneObject.GetMailAddress().get(21));
		assertEquals("t104598007+sss@ntut.org.tw", cloneObject.GetMailAddress().get(22));
		assertEquals("t104598007+ttt@ntut.org.tw", cloneObject.GetMailAddress().get(23));
		assertEquals("t104598007+uuu@ntut.org.tw", cloneObject.GetMailAddress().get(24));
		assertEquals("t104598007+vvv@ntut.org.tw", cloneObject.GetMailAddress().get(25));
		assertEquals("t104598007+www@ntut.org.tw", cloneObject.GetMailAddress().get(26));
		assertEquals("t104598007+xxx@ntut.org.tw", cloneObject.GetMailAddress().get(27));
		assertEquals("t104598007+yyy@ntut.org.tw", cloneObject.GetMailAddress().get(28));
		assertEquals("t104598007+zzz@ntut.org.tw", cloneObject.GetMailAddress().get(29));
	}

	@Test
	public void testSetStudents() {
		studentInfomation.SetStudents();
		List<String> students = studentInfomation.GetStudents();
		assertEquals("Alice", students.get(0));
		assertEquals("Bob", students.get(1));
		assertEquals("Chris", students.get(2));
		assertEquals("David", students.get(3));
		assertEquals("Eva", students.get(4));
		assertEquals("FLT", students.get(5));
		assertEquals("GTA", students.get(6));
		assertEquals("HIA", students.get(7));
		assertEquals("IT", students.get(8));
		assertEquals("Jack", students.get(9));

		assertEquals("Kevin", students.get(10));
		assertEquals("Lee", students.get(11));
		assertEquals("Mo", students.get(12));
		assertEquals("Net", students.get(13));
		assertEquals("Ox", students.get(14));
		assertEquals("P", students.get(15));
		assertEquals("Q", students.get(16));
		assertEquals("R", students.get(17));
		assertEquals("S", students.get(18));
		assertEquals("T", students.get(19));

		assertEquals("U", students.get(20));
		assertEquals("V", students.get(21));
		assertEquals("W", students.get(22));
		assertEquals("X", students.get(23));
		assertEquals("Y", students.get(24));
		assertEquals("Z", students.get(25));
		assertEquals("0.0", students.get(26));
		assertEquals("0w0", students.get(27));
		assertEquals("0o0", students.get(28));
		assertEquals("0A0", students.get(29));
	}

	@Test
	public void testSetMailAddress() {
		studentInfomation.SetMailAddress();
		List<String> mailAddresses = studentInfomation.GetMailAddress();
		assertEquals("t104598007+a@ntut.org.tw", mailAddresses.get(0));
		assertEquals("t104598007+b@ntut.org.tw", mailAddresses.get(1));
		assertEquals("t104598007+c@ntut.org.tw", mailAddresses.get(2));
		assertEquals("t104598007+d@ntut.org.tw", mailAddresses.get(3));
		assertEquals("t104598007+aaa@ntut.org.tw", mailAddresses.get(4));
		assertEquals("t104598007+bbb@ntut.org.tw", mailAddresses.get(5));
		assertEquals("t104598007+ccc@ntut.org.tw", mailAddresses.get(6));
		assertEquals("t104598007+ddd@ntut.org.tw", mailAddresses.get(7));
		assertEquals("t104598007+eee@ntut.org.tw", mailAddresses.get(8));
		assertEquals("t104598007+fff@ntut.org.tw", mailAddresses.get(9));

		assertEquals("t104598007+ggg@ntut.org.tw", mailAddresses.get(10));
		assertEquals("t104598007+hhh@ntut.org.tw", mailAddresses.get(11));
		assertEquals("t104598007+iii@ntut.org.tw", mailAddresses.get(12));
		assertEquals("t104598007+jjj@ntut.org.tw", mailAddresses.get(13));
		assertEquals("t104598007+kkk@ntut.org.tw", mailAddresses.get(14));
		assertEquals("t104598007+lll@ntut.org.tw", mailAddresses.get(15));
		assertEquals("t104598007+mmm@ntut.org.tw", mailAddresses.get(16));
		assertEquals("t104598007+nnn@ntut.org.tw", mailAddresses.get(17));
		assertEquals("t104598007+ooo@ntut.org.tw", mailAddresses.get(18));
		assertEquals("t104598007+ppp@ntut.org.tw", mailAddresses.get(19));

		assertEquals("t104598007+qqq@ntut.org.tw", mailAddresses.get(20));
		assertEquals("t104598007+rrr@ntut.org.tw", mailAddresses.get(21));
		assertEquals("t104598007+sss@ntut.org.tw", mailAddresses.get(22));
		assertEquals("t104598007+ttt@ntut.org.tw", mailAddresses.get(23));
		assertEquals("t104598007+uuu@ntut.org.tw", mailAddresses.get(24));
		assertEquals("t104598007+vvv@ntut.org.tw", mailAddresses.get(25));
		assertEquals("t104598007+www@ntut.org.tw", mailAddresses.get(26));
		assertEquals("t104598007+xxx@ntut.org.tw", mailAddresses.get(27));
		assertEquals("t104598007+yyy@ntut.org.tw", mailAddresses.get(28));
		assertEquals("t104598007+zzz@ntut.org.tw", mailAddresses.get(29));
	}

}
