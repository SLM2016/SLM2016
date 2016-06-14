package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courseManager.Course;

public class CourseTest {
	private Course course_;

	@Before
	public void setUp() throws Exception {
		course_ = new Course("teddysoft-course01-1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		String courseName = "Scurm敏捷方法實作班";
		course_.setCourseName(courseName);
		String courseCode = "Scurm";
		course_.setCourseCode(courseCode);
		String type = "公開班";
		course_.setType(type);
		String batch = "401";
		course_.setBatch(batch);
		String date = "2016/6/24";
		course_.addDate(date);
		int duration = 120;
		course_.setDuration(duration);
		String ticketType = "一般票 ";
		course_.addTicketType(ticketType);
		int price = 33000;
		course_.addPrice(price);
		String location = "台北市中正區延平南路12號4樓";
		course_.setLocation(location);
		String lecturer = "Teddy";
		course_.setLecturer(lecturer);
		String status = "準備中";
		course_.setStatus(status);
		Course cloneObject = course_.clone();
		assertEquals("teddysoft-course01-1", cloneObject.getCourseId());
		assertEquals(courseName, cloneObject.getCourseName());
		assertEquals(courseCode, cloneObject.getCourseCode());
		assertEquals(type, cloneObject.getType());
		assertEquals(batch, cloneObject.getBatch());
		assertEquals(1, cloneObject.getDates().size());
		assertEquals(date, cloneObject.getDates().get(0));
		assertEquals(duration, cloneObject.getDuration());
		assertEquals(1, cloneObject.getTicketTypes().size());
		assertEquals(ticketType, cloneObject.getTicketTypes().get(0));
		assertEquals(1, cloneObject.getPrices().size());
		assertEquals(price, cloneObject.getPrices().get(0).intValue());
		assertEquals(location, cloneObject.getLocation());
		assertEquals(lecturer, cloneObject.getLecturer());
		assertEquals(status, cloneObject.getStatus());
	}

	@Test
	public void testSetCourseName() {
		String courseName = "Scurm敏捷方法實作班";
		course_.setCourseName(courseName);
		assertEquals(courseName, course_.getCourseName());
	}

	@Test
	public void testSetType() {
		String type = "公開班";
		course_.setType(type);
		assertEquals(type, course_.getType());
	}

	@Test
	public void testSetBatch() {
		String batch = "401";
		course_.setBatch(batch);
		assertEquals(batch, course_.getBatch());
	}

	@Test
	public void testAddDate() {
		String date = "2016/6/24";
		course_.addDate(date);
		assertEquals(date, course_.getDates().get(0));
	}

	@Test
	public void testDeleteDate() {
		course_.addDate("2016/6/24");
		course_.addDate("2016/6/25");
		assertEquals(2, course_.getDates().size());
		course_.deleteDate(0);
		assertEquals(1, course_.getDates().size());
		assertEquals("2016/6/25", course_.getDates().get(0));
	}

	@Test
	public void testSetDuration() {
		int duration = 120;
		course_.setDuration(duration);
		assertEquals(duration, course_.getDuration());
	}

	@Test
	public void testAddTicketType() {
		String ticketType = "一般票 ";
		course_.addTicketType(ticketType);
		assertEquals(1, course_.getTicketTypes().size());
		assertEquals(ticketType, course_.getTicketTypes().get(0));
	}

	@Test
	public void testDeleteTicketType() {
		course_.addTicketType("一般票");
		course_.addTicketType("早鳥票");
		assertEquals(2, course_.getTicketTypes().size());
		course_.deleteTicketType(0);
		assertEquals(1, course_.getTicketTypes().size());
		assertEquals("早鳥票", course_.getTicketTypes().get(0));

	}

	@Test
	public void testAddPrice() {
		int price = 33000;
		course_.addPrice(price);
		assertEquals(1, course_.getPrices().size());
		assertEquals(price, course_.getPrices().get(0).intValue());
	}

	@Test
	public void testDeletePrice() {
		course_.addPrice(10000);
		course_.addPrice(20000);
		assertEquals(2, course_.getPrices().size());
		course_.deletePrice(0);
		assertEquals(1, course_.getPrices().size());
		assertEquals(20000, course_.getPrices().get(0).intValue());
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
		String status = "準備中";
		course_.setStatus(status);
		assertEquals(status, course_.getStatus());
	}

	@Test
	public void testAddCcAddress() {
		String ccAddress = "test@test";
		course_.addCcAddresses(ccAddress);
		assertEquals(ccAddress, course_.getCcAddresses().get(0));
	}

	@Test
	public void testDeleteCcAddress() {
		course_.addCcAddresses("test@test");
		course_.addCcAddresses("test2@test2.test");
		assertEquals(2, course_.getCcAddresses().size());
		course_.deleteCcAddresses(0);
		assertEquals(1, course_.getCcAddresses().size());
		assertEquals("test2@test2.test", course_.getCcAddresses().get(0));
	}

	@Test
	public void testSetHyperlink() {
		String hyperlink = "www.test.com";
		course_.setHyperlink(hyperlink);
		assertEquals(hyperlink, course_.getHyperlink());
	}

	@Test
	public void testSetCourseCode() {
		String courseCode = "DP";
		course_.setCourseCode(courseCode);
		assertEquals(courseCode, course_.getCourseCode());
	}
}
