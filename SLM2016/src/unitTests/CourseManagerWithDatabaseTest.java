package unitTests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courseManager.Course;
import courseManager.CourseManagerWithDatabase;

public class CourseManagerWithDatabaseTest {
	private CourseManagerWithDatabase courseManagerWithDb_;

	@Before
	public void setUp() throws Exception {
		courseManagerWithDb_ = new CourseManagerWithDatabase();
		Course course = new Course("test01");
		course.setCourseName("Scurm敏捷方法實作班");
		course.setType("公開班");
		course.setBatch("401");
		course.addDate("2016-05-10");
		course.setDuration(120);
		course.addTicketType("VIP");
		course.addPrice(100);
		course.setLocation("Lab1421");
		course.setLecturer("Teddy");
		course.setStatus("準備中");
		course.addCcAddresses("test@test.com");
		course.setHyperlink("http://teddysoft.tw");
		courseManagerWithDb_.addCourseIntoDatabase(course);
	}

	@After
	public void tearDown() throws Exception {
		courseManagerWithDb_.deleteCourseFromDatabase("test01");
	}

	@Test
	public void testGetCourseFromDatabase() throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		String result = courseManagerWithDb_.getCourseFromDatabase(courses);
		assertEquals("Success", result);
		assertEquals("Scurm敏捷方法實作班", courses.get(0).getCourseName());
		assertEquals("公開班", courses.get(0).getType());
		assertEquals("401", courses.get(0).getBatch());
		assertEquals("2016-05-10", courses.get(0).getDates().get(0));
		assertEquals(120, courses.get(0).getDuration());
		assertEquals("VIP", courses.get(0).getTicketTypes().get(0));
		assertEquals(100, courses.get(0).getPrices().get(0).intValue());
		assertEquals("Lab1421", courses.get(0).getLocation());
		assertEquals("Teddy", courses.get(0).getLecturer());
		assertEquals("準備中", courses.get(0).getStatus());
		assertEquals("test@test.com", courses.get(0).getCcAddresses().get(0));
		assertEquals("http://teddysoft.tw", courses.get(0).getHyperlink());
	}

	@Test
	public void testAddCourseIntoDatabase() throws SQLException {
		Course course = new Course("test02");
		course.setCourseName("Scurm敏捷方法實作班");
		course.setType("公開班");
		course.setBatch("401");
		course.addDate("2016-05-10");
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
		courseManagerWithDb_.deleteCourseFromDatabase("test02");
	}

	@Test
	public void testDeleteCourseFromDatabase() throws SQLException {
		String result = courseManagerWithDb_.deleteCourseFromDatabase("test01");
		assertEquals("Success", result);
	}
}
