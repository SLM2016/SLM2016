package unitTests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courseManager.Course;
import courseManager.CourseManagerWithDatabase;

public class CourseManagerWithDbTest {
	private CourseManagerWithDatabase courseManagerWithDb_;

	@Before
	public void setUp() throws Exception {
		courseManagerWithDb_ = new CourseManagerWithDatabase();
		//Course course = new Course("test01");
//		course.setCourseName("Scurm敏捷方法實作班");
//		course.setType("公開班");
//		course.setBatch("401");
//		course.addDate("2016/5/10");
//		course.setDuration(120);
//		course.addTicketType("VIP");
//		course.addPrice(100);
//		course.setLocation("Lab1421");
//		course.setLecturer("Teddy");
//		course.setStatus("準備中");
//		course.addCcAddresses("test@test.com");
//		course.setHyperlink("http://teddysoft.tw");
//		courseManagerWithDb_.addCourseIntoDatabase(course);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCourseFromDatabase() {

	}

	@Test
	public void testAddCourseIntoDatabase() throws SQLException {
		Course course = new Course("test02");
		course.setCourseName("Scurm敏捷方法實作班");
		course.setType("公開班");
		course.setBatch("401");
		course.addDate("2016/5/10");
		course.setDuration(120);
		course.addTicketType("VIP");
		course.addPrice(100);
		course.setLocation("Lab1421");
		course.setLecturer("Teddy");
		course.setStatus("準備中");
		course.addCcAddresses("test@test.com");
		course.setHyperlink("http://teddysoft.tw");
		String result = courseManagerWithDb_.addCourseIntoDatabase(course);
		assertEquals("Success", result);
	}

	@Test
	public void testDeleteCourseFromDatabase() {

	}
}
