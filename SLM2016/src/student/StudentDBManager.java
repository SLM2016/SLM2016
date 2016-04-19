package student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javafx.util.Pair;
import util.SLMDBUtility;

public class StudentDBManager {
	SLMDBUtility slmDBUtility = null;

	public StudentDBManager() {
		super();
		slmDBUtility = new SLMDBUtility();
	}

	public ArrayList<HashMap> getStudents() throws SQLException {
		ArrayList<HashMap> arrayList = new ArrayList<HashMap>();
		String sql = "select * from `student`;";
		ResultSet result = slmDBUtility.selectSQL(sql);
		while (result.next()) {
			// Retrieve by column name
			HashMap map = new HashMap();
			int id = result.getInt("id");
			String name = result.getString("name");
			String phone = result.getString("phone");
			map.put("id", id);
			map.put("name", name);
			map.put("phone", phone);
			arrayList.add(map);
		}
		return arrayList;
	}

	public boolean insertStudent(String name, String email, String nickname, String phone, String company,
			String apartment, String title, String ticketType, String vegeMeat, String receiptType,
			String companyNameAndEIN, String classInfo, String hasScrum, String flowOk, String teamMembers,
			String comment, String timestamp) throws SQLException {
		String sql = String.format(
				"INSERT INTO `student_info`(`name`, `email.`, `nickname`, `phone`, `company`, `apartment`, `title`, `ticket_type`, `vege_meat`, `receipt_type`, `company_name_and_EIN`, `class_info`, `has_scrum`, `flow_ok`, `team_members`, `comment`, `timestamp`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s') ",
				name, email, nickname, phone, company, apartment, title, ticketType, vegeMeat, receiptType,
				companyNameAndEIN, classInfo, hasScrum, flowOk, teamMembers, comment, timestamp);
		String duplicate = String.format(
				"ON DUPLICATE KEY UPDATE `name` = '%s', `email.` = '%s', `nickname` = '%s', `phone` = '%s', `company` = '%s', `apartment` = '%s', `title` = '%s', `ticket_type` = '%s', `vege_meat` = '%s', `receipt_type` = '%s', `company_name_and_EIN` = '%s', `class_info` = '%s', `has_scrum` = '%s', `flow_ok` = '%s', `team_members` = '%s', `comment` = '%s', `timestamp` = '%s';",
				name, email, nickname, phone, company, apartment, title, ticketType, vegeMeat, receiptType,
				companyNameAndEIN, classInfo, hasScrum, flowOk, teamMembers, comment, timestamp);

		if (slmDBUtility.insertSQL((sql + duplicate))) {
			return true;
		} else {
			return false;
		}
	}
}
