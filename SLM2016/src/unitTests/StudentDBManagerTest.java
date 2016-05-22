package unitTests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import student.StudentDBManager;
import student.StudentModel;
import student.StudentSendMailData;

import util.SLMDBUtility;

public class StudentDBManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStudentDBManager() {
		ArrayList<HashMap<String, String>> array = new ArrayList<HashMap<String, String>>();
		boolean boolResult = false;
		StudentDBManager studentDBManager = new StudentDBManager();
		StudentModel s = new StudentModel();
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		s.setTimestamp(timestamp);
		s.setTicketType("票的種類");
		// String name = "測試名";
		s.setName("測試名");
		String nickname = "測試用";
		s.setNickname(nickname);
		String email = "testHelloEmail@gmail.com";
		s.setEmail(email);
		String phone = "0912-987654";
		s.setPhone(phone);
		String company = "測試公司";
		s.setCompany(company);
		String apartment = "測試部門";
		s.setApartment(apartment);
		String title = "測試用";
		s.setTitle(title);
		String vegeMeat = "葷食";
		s.setVegeMeat(vegeMeat);
		String receiptType = "二聯式";
		s.setReceiptType(receiptType);

		try {
			studentDBManager.insertStudent(s);

			array = studentDBManager.getStudentByPhone(phone);

		} catch (SQLException e) {
			fail("Exception Error");
		}
		assertEquals(array.size(), 1);
		assertEquals(array.get(0).get("phone"), phone);
		try {
			boolResult = studentDBManager.deleteStudent(phone);

		} catch (SQLException e) {
			fail("Exception Error");
		}
		assertEquals(boolResult, true);
	}
		
	@Test
	public void testGetMailInfo() {	
		
		StudentDBManager studentDBManager = new StudentDBManager();
		StudentSendMailData[] studentSendMailData = new StudentSendMailData[1];
		studentSendMailData[0] = new StudentSendMailData();
		studentSendMailData[0].address = "123@gmail.com";
		studentSendMailData[0].courseId = "teddysoftware-course-03-1";
		studentSendMailData[0].studentId = 1;
		studentSendMailData[0].studentName = "Chen Teddy";
		
		try {
			String result = studentDBManager.getSendMailInfo(studentSendMailData);
			GsonBuilder gsonBuilder = new GsonBuilder();
	        Gson gson = gsonBuilder.create();
	        TestCourseData[] queryData = gson.fromJson(result, TestCourseData[].class);
			assertEquals("teddysoftware-course-03-1", queryData[0].id);
			assertEquals("單元測試與持續整合實作班", queryData[0].name);
			assertEquals("20", queryData[0].duration);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	class TestCourseData {
		public String duration;
		public String name;
		public String id;
	}

}
