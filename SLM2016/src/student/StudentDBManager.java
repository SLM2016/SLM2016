package student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import util.SLMDBUtility;

public class StudentDBManager {
	SLMDBUtility slmDBUtility = null;

	public StudentDBManager() {
		super();
		slmDBUtility = new SLMDBUtility();
	}

	public String getStudentList() throws SQLException {
		String sql = "select * from `student_info`;";
		ArrayList<HashMap<String, String>> result = slmDBUtility.selectSQL(sql);
		Gson g = new Gson();
		// String columnName, columnValue = null;
		// JsonObject element = null;
		// JsonArray jsonArray = new JsonArray();
		// ResultSetMetaData rsmd = null;
		//
		// try {
		// rsmd = (ResultSetMetaData) result.getMetaData();
		// while (result.next()) {
		// element = new JsonObject();
		// for (int i = 0; i < rsmd.getColumnCount(); i++) {
		// columnName = rsmd.getColumnName(i + 1);
		// columnValue = result.getString(columnName);
		// element.addProperty(columnName, columnValue);
		// }
		// jsonArray.add(element);
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

		// return jsonArray.toString();
		return g.toJson(result);
	}

	public String getStudentListByCourseId(String courseId) throws SQLException {
		String sql = String.format("SELECT * FROM `student_info` WHERE `fk_course_info_id` = '%s';", courseId);
		ArrayList<HashMap<String, String>> result = slmDBUtility.selectSQL(sql);
		Gson g = new Gson();
		return g.toJson(result);
	}

	// public ArrayList<HashMap> getStudents() throws SQLException {
	// ArrayList<HashMap> arrayList = new ArrayList<HashMap>();
	// String sql = "select * from `student_info`;";
	// ResultSet result = slmDBUtility.selectSQL(sql);
	// while (result.next()) {
	// // Retrieve by column name
	// HashMap map = new HashMap();
	// int id = result.getInt("id");
	// String name = result.getString("name");
	// String phoneNumber = result.getString("phone");
	// map.put("id", id);
	// map.put("name", name);
	// map.put("phone", phoneNumber);
	// arrayList.add(map);
	// }
	// return arrayList;
	// }
	//
	// public ArrayList<HashMap> getStudentByPhone(String phone) throws
	// SQLException {
	// ArrayList<HashMap> arrayList = new ArrayList<HashMap>();
	// String sql = String.format("select * from `student_info` where `phone` =
	// '%s';", phone);
	// ResultSet result = slmDBUtility.selectSQL(sql);
	// while (result.next()) {
	// // Retrieve by column name
	// HashMap map = new HashMap();
	// int id = result.getInt("id");
	// String name = result.getString("name");
	// String phoneNumber = result.getString("phone");
	// map.put("id", id);
	// map.put("name", name);
	// map.put("phone", phoneNumber);
	// arrayList.add(map);
	// }
	// return arrayList;
	// }

	public boolean insertStudent(StudentModel studentModel) throws SQLException {

		String sql = String.format(
				"INSERT INTO `student_info`(`name`, `email`, `nickname`, `phone`, `company`, `apartment`, `title`, `ticket_type`, `ticket_price`, `receipt_type`, `receipt_company_name`, `receipt_company_EIN`, `receipt_EIN`,`student_status`, `payment_status`, `receipt_status`, `vege_meat`, `team_members`, `comment`, `timestamp`, `fk_course_info_id`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
				studentModel.getName(), studentModel.getEmail(), studentModel.getNickname(), studentModel.getPhone(),
				studentModel.getCompany(), studentModel.getApartment(), studentModel.getTitle(),
				studentModel.getTicketType(), studentModel.getTicketPrice(), studentModel.getReceiptType(),
				studentModel.getReceiptCompanyName(), studentModel.getReceiptCompanyEIN(), studentModel.getReceiptEIN(),
				"已報名", "未繳費", "未開立", studentModel.getVegeMeat(), studentModel.getTeamMembers(),
				studentModel.getComment(), studentModel.getTimestamp(), studentModel.getFkCourseInfoId());

		// "ON DUPLICATE KEY UPDATE `name` = '%s'

		if (slmDBUtility.insertSQL((sql))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteStudent(String phone) throws SQLException {
		String sql = String.format("DELETE FROM `student_info` WHERE `student_info`.`phone` = '%s'", phone);
		if (slmDBUtility.deleteSQL(sql)) {
			return true;
		} else {
			return false;
		}
	}
}
