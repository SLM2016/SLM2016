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

import student.StudentDBManager;

public class StudentDBManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStudentDBManager() {
		ArrayList<HashMap> array = new ArrayList<HashMap>();
		boolean boolResult = false;
		StudentDBManager studentDBManager = new StudentDBManager();
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String ticketType = "票的種類";
		String name = "測試名";
		String nickname = "測試用";
		String email = "testHelloEmail@gmail.com";
		String phone = "0912399888";
		String company = "測試公司";
		String apartment = "測試部門";
		String title = "測試用";
		String vegeMeat = "葷食";
		String receiptType = "二聯式";
		String companyNameAndEIN = "";
		String classInfo = "測試";
		String hasScrum = "測試";
		String flowOk = "測試";
		String teamMembers = "測試";
		String comment = "測試";

		try {
			studentDBManager.insertStudent(name, email, nickname, phone, company, apartment, title, ticketType,
					vegeMeat, receiptType, companyNameAndEIN, classInfo, hasScrum, flowOk, teamMembers, comment,
					timestamp);

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

}
