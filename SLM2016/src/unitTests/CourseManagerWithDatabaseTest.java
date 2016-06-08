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
		course.setCourseCode("Scurm");
		course.setType("公開班");
		course.setBatch("401");
		course.addDate("2016-05-10");
		course.setDuration(120);
		course.addTicketType("VIP");
		course.addPrice(100);
		course.setLocation("Lab1421");
		course.setLecturer("Teddy");
		course.setStatus("報名中");
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
		assertEquals("Scurm敏捷方法實作班", courses.get(courses.size() - 1).getCourseName());
		assertEquals("Scurm", courses.get(courses.size() - 1).getCourseCode());
		assertEquals("公開班", courses.get(courses.size() - 1).getType());
		assertEquals("401", courses.get(courses.size() - 1).getBatch());
		assertEquals("2016-05-10", courses.get(courses.size() - 1).getDates().get(0));
		assertEquals(120, courses.get(courses.size() - 1).getDuration());
		assertEquals("VIP", courses.get(courses.size() - 1).getTicketTypes().get(0));
		assertEquals(100, courses.get(courses.size() - 1).getPrices().get(0).intValue());
		assertEquals("Lab1421", courses.get(courses.size() - 1).getLocation());
		assertEquals("Teddy", courses.get(courses.size() - 1).getLecturer());
		assertEquals("報名中", courses.get(courses.size() - 1).getStatus());
		assertEquals("test@test.com", courses.get(courses.size() - 1).getCcAddresses().get(0));
		assertEquals("http://teddysoft.tw", courses.get(courses.size() - 1).getHyperlink());
	}

	@Test
	public void testGetCourseSimpleDataFromDatabase() throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		String result = courseManagerWithDb_.getCourseSimpleDataFromDatabase(courses);
		assertEquals("Success", result);
		assertEquals("Scurm敏捷方法實作班", courses.get(courses.size() - 1).getCourseName());
		assertEquals("401", courses.get(courses.size() - 1).getBatch());
		assertEquals("報名中", courses.get(courses.size() - 1).getStatus());
	}

	@Test
	public void testAddCourseIntoDatabase() throws SQLException {
		Course course = new Course("test02");
		course.setCourseName("Scurm敏捷方法實作班");
		course.setCourseCode("Scurm");
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

	@Test
	public void testGetCcAddressByCourseId() throws SQLException {
		String result = courseManagerWithDb_.getCcAddressByCourseId("test01");
		assertEquals("test@test.com", result);
	}

	@Test
	public void testGetHyperlinkByCourseId() throws SQLException {
		String result = courseManagerWithDb_.getHyperlinkByCourseId("test01");
		assertEquals("http://teddysoft.tw", result);
	}

	@Test
	public void testGetSignUpCourseIdByCourseNameAndBatchAndStatus() throws SQLException {
		String result = courseManagerWithDb_.getCourseIdByCourseNameAndBatchAndStatus("Scurm敏捷方法實作班", "401", "報名中");
		assertEquals("test01", result);
	}
}
