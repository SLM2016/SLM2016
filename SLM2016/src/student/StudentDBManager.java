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
	
	public String getStudentList()  throws SQLException {
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
	
	public ArrayList<HashMap<String, String>> getStudentByPhone(String phone) throws SQLException {

		String sql = String.format("SELECT * FROM `student_info` WHERE `phone` = '%s';", phone);
		ArrayList<HashMap<String, String>> result = slmDBUtility.selectSQL(sql);

		return result;
	}

	public String getSendMailInfo(StudentSendMailData[] studentSendMailData) throws SQLException {	
		ArrayList<HashMap<String, String>> queryData = new ArrayList<HashMap<String, String>>();
		for(int i = 0; i < studentSendMailData.length; i++){
			String sql = "select courseInfo.id, courseInfo.name, courseInfo.duration,  courseDate.date "
					+ "from `course_info` courseInfo, `course_has_date` courseDate "
					+ "where courseInfo.id = "+"\""+studentSendMailData[i].courseId+"\" AND "
					+ "courseDate.fk_course_id = "+"\""+studentSendMailData[i].courseId+"\" "
					+ "ORDER BY date";;
			queryData.addAll(slmDBUtility.selectSQL(sql));	
		}	
		return new Gson().toJson(queryData);
	}
	
	public String getCertificationInfo(String studentID) throws SQLException {	
		String sql = "select certification_img from `student_info` where id = "+studentID;;		      		
		return new Gson().toJson(slmDBUtility.selectSQL(sql));
	}

	public boolean insertStudent(StudentModel studentModel) throws SQLException {

		String sql = String.format(
				"INSERT INTO `student_info`(`name`, `email`, `nickname`, `phone`, `company`, `apartment`, `title`, `ticket_type`, `ticket_price`, `receipt_type`, `receipt_company_name`, `receipt_company_EIN`, `receipt_EIN`,`student_status`, `payment_status`, `receipt_status`, `vege_meat`, `team_members`, `comment`, `timestamp`, `fk_course_info_id`,`certification_img`,`certification_pdf`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
				studentModel.getName(), studentModel.getEmail(), studentModel.getNickname(), studentModel.getPhone(),
				studentModel.getCompany(), studentModel.getApartment(), studentModel.getTitle(),
				studentModel.getTicketType(), studentModel.getTicketPrice(), studentModel.getReceiptType(),
				studentModel.getReceiptCompanyName(), studentModel.getReceiptCompanyEIN(), studentModel.getReceiptEIN(),
				"已報名", "未繳費", "未開立", studentModel.getVegeMeat(), studentModel.getTeamMembers(),
				studentModel.getComment(), studentModel.getTimestamp(), studentModel.getFkCourseInfoId(),
				studentModel.getCertificationImg(), studentModel.getCertificationPdf());

		// "ON DUPLICATE KEY UPDATE `name` = '%s'

		if (slmDBUtility.insertSQL((sql))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateStudent(StudentModel studentModel) {
		String sql = String.format(
				"UPDATE `student_info` SET `name`='%s',`email`='%s',`nickname`='%s',`phone`='%s',`company`='%s',`apartment`='%s',`title`='%s',`ticket_type`='%s',`ticket_price`='%s',`receipt_type`='%s',`receipt_company_name`='%s',`receipt_company_EIN`='%s',`receipt_EIN`='%s',`student_status`='%s',`payment_status`='%s',`receipt_status`='%s',`vege_meat`='%s',`team_members`='%s',`comment`='%s',`timestamp`='%s',`fk_course_info_id`='%s',`certification_img` = '%s', `certification_pdf` = '%s' WHERE `id` = '%s'",
				studentModel.getName(), studentModel.getEmail(), studentModel.getNickname(), studentModel.getPhone(),
				studentModel.getCompany(), studentModel.getApartment(), studentModel.getTitle(),
				studentModel.getTicketType(), studentModel.getTicketPrice(), studentModel.getReceiptType(),
				studentModel.getReceiptCompanyName(), studentModel.getReceiptCompanyEIN(), studentModel.getReceiptEIN(),
				studentModel.getStudentStatus(), studentModel.getPaymentStatus(), studentModel.getReceiptStatus(),
				studentModel.getVegeMeat(), studentModel.getTeamMembers(), studentModel.getComment(),
				studentModel.getTimestamp(), studentModel.getFkCourseInfoId(), studentModel.getCertificationImg(),
				studentModel.getCertificationPdf(), studentModel.getId());
		if (slmDBUtility.updateSQL((sql))) {
			return true;
		} else {
			return false;
		}
	}

	public StudentModel getStudentById(String id) throws SQLException {

		String sql = String.format("select * from `student_info` where `id` = %s", id);
		ArrayList<HashMap<String, String>> result = slmDBUtility.selectSQL(sql);

		if (result.size() > 0) {
			HashMap<String, String> map = result.get(0);
			StudentModel studentModel = new StudentModel();

			studentModel.setId(map.get("id"));
			studentModel.setName(map.get("name"));
			studentModel.setNickname(map.get("nickname"));
			studentModel.setEmail(map.get("email"));
			studentModel.setPhone(map.get("phone"));
			studentModel.setCompany(map.get("company"));
			studentModel.setApartment(map.get("apartment"));
			studentModel.setTitle(map.get("title"));
			studentModel.setTicketType(map.get("ticket_type"));
			studentModel.setTicketPrice(map.get("ticket_price"));
			studentModel.setReceiptType(map.get("receipt_type"));
			studentModel.setReceiptCompanyName(map.get("receipt_company_name"));
			studentModel.setFkCourseInfoId(map.get("fk_course_info_id"));
			studentModel.setReceiptCompanyEIN(map.get("receipt_company_EIN"));
			studentModel.setReceiptEIN(map.get("receipt_EIN"));
			studentModel.setTeamMembers(map.get("team_members"));
			studentModel.setComment(map.get("comment"));
			studentModel.setVegeMeat(map.get("vege_meat"));
			studentModel.setTimestamp(map.get("timestamp"));
			studentModel.setStudentStatus(map.get("student_status"));
			studentModel.setPaymentStatus(map.get("payment_status"));
			studentModel.setReceiptStatus(map.get("receipt_status"));
			studentModel.setCertificationImg(map.get("certification_img"));
			studentModel.setCertificationPdf(map.get("certification_pdf"));

			return studentModel;

		} else {
			return null;
		}
		// "ON DUPLICATE KEY UPDATE `name` = '%s'
	}

	public boolean deleteStudent(String phone) throws SQLException {
		String sql = String.format("DELETE FROM `student_info` WHERE `student_info`.`phone` = '%s'", phone);
		if (slmDBUtility.deleteSQL(sql)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteStudentsByCourseId(String courseId) throws SQLException {
		String sql = String.format("DELETE FROM `student_info` WHERE `fk_course_info_id` =  '%s'", courseId);
		if (slmDBUtility.deleteSQL(sql)) {
			return true;
		} else {
			return false;
		}
	}

	//only get course data
	public boolean insertGetMailInfo(String id, String name, String duration, String fk_status_id) throws SQLException {
		String sql = String.format(
				"INSERT INTO `course_info`(`id`,`name`,`duration`,`fk_status_id`) VALUES ('%s','%s','%s','%s')", 
				id,name,duration,fk_status_id);
		String duplicate = String.format(
				"ON DUPLICATE KEY UPDATE `id` = '%s',`name` = '%s',`duration` = '%s',`fk_status_id` = '%s'",
				id,name,duration,fk_status_id);
		if (slmDBUtility.insertSQL((sql + duplicate))) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteGetMailInfo(String id) throws SQLException {
		String sql = String.format("DELETE FROM `course_info` WHERE `course_info`.`id` = '%s'", id);
		if (slmDBUtility.deleteSQL(sql)) {
			return true;
		} else {
			return false;
		}
	}
}
