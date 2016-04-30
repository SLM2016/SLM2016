package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataClass.Course;

public class CourseTest {
	private Course course_;

	@Before
	public void setUp() throws Exception {
		course_ = new Course();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetCourseName() {
		String courseName = "Scurm敏捷方法實作班";
		course_.setCourseName(courseName);
		assertEquals(courseName, course_.getCourseName());
	}

	@Test
	public void testSetDatch() {
		String datch = "401";
		course_.setDatch(datch);
		assertEquals(datch, course_.getDatch());
	}

	@Test
	public void testSetDate() {
		String date = "2016/6/24、25、26 (五、六、日)";
		course_.setDate(date);
		assertEquals(date, course_.getDate());
	}

	@Test
	public void testSetTime() {
		String time = "09:30~16:30 共18小時";
		course_.setTime(time);
		assertEquals(time, course_.getTime());
	}

	@Test
	public void testSetTicket() {
		String ticket = "一般票 ";
		course_.setTicket(ticket);
		assertEquals(ticket, course_.getTicket());
	}

	@Test
	public void testSetPrice() {
		int price = 33000;
		course_.setPrice(price);
		assertEquals(price, course_.getPrice());
	}

	@Test
	public void testSetLocation() {
		String location = "台北市中正區延平南路12號4樓";
		course_.setLocation(location);
		assertEquals(location, course_.getLocation());
	}

	@Test
	public void testSetLecturer() {
		String lecturer = "Teddy";
		course_.setLecturer(lecturer);
		assertEquals(lecturer, course_.getLecturer());
	}

	@Test
	public void testSetStatus() {
		String status = "未開課";
		course_.setStatus(status);
		assertEquals(status, course_.getStatus());
	}
}
