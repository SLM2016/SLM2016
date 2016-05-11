package courseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import javafx.util.Pair;
import util.SqlHelper;

public class CourseManagerWithDatabase {
	private int databaseDataIdMax_;
	private List<Pair<String, String>> courseStatus_ = new ArrayList<Pair<String, String>>();

	public CourseManagerWithDatabase() {
		databaseDataIdMax_ = 1;
		try {
			getCourseStatusTable();
			getAllCourseId();
		} catch (SQLException e) {

		}
	}

	// public int getDatabaseDataIdMax() {
	// return databaseDataIdMax_;
	// }

	private String getCourseStatusTable() throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "SELECT * FROM `slm2016`.`course_status`";
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		while (data.next()) {
			courseStatus_.add(new Pair<String, String>(data.getString("id"), data.getString("name")));
		}
		return result;
	}

	private String getAllCourseId() throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "SELECT id FROM `course_info`";
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		while (data.next()) {
			String id = data.getString("id");
			int index = id.lastIndexOf("-");
			if (index != -1) {
				id = id.substring(index + 1);
				databaseDataIdMax_ = Integer.parseInt(id);
				databaseDataIdMax_++;
			}
		}
		return result;
	}

	public String getCourseFromDatabase(List<Course> courses) throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "SELECT * FROM `slm2016`.`course_info`";
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		if (result != "Success")
			return result;
		while (data.next()) {
			String id = data.getString("id");
			String fk = data.getString("fk_status_id");
			Course course = new Course(id);
			course.setCourseName(data.getString("name"));
			course.setType(data.getString("type"));
			course.setBatch(data.getString("batch"));
			course.setDuration(Integer.parseInt(data.getString("duration")));
			course.setLocation(data.getString("location"));
			course.setLecturer(data.getString("lecturer"));
			course.setHyperlink(data.getString("page_link"));
			for (int i = 0; i < courseStatus_.size(); i++) {
				if (courseStatus_.get(i).getKey().equals(fk)) {
					course.setStatus(courseStatus_.get(i).getValue());
					break;
				}
			}
			String temp;
			temp = getCourseFromTicket(course, id);
			if (temp != "Success")
				return temp;
			temp = getCourseFromDate(course, id);
			if (temp != "Success")
				return temp;
			temp = getCourseFromCcAddress(course, id);
			if (temp != "Success")
				return temp;

			courses.add(course);
		}
		return result;
	}

	private String getCourseFromTicket(Course course, String id) throws SQLException {
		String sqlString = "SELECT type,price FROM `course_has_ticket` WHERE `fk_course_id`='" + id + "'";
		SqlHelper helper = new SqlHelper();
		CachedRowSet ticketData = new CachedRowSetImpl();
		String result = helper.excuteSql(sqlString, ticketData);
		while (ticketData.next()) {
			course.addTicketType(ticketData.getString("type"));
			course.addPrice(Integer.parseInt(ticketData.getString("price")));
		}
		ticketData.close();
		return result;
	}

	private String getCourseFromDate(Course course, String id) throws SQLException {
		String sqlString = "SELECT date FROM `course_has_date` WHERE `fk_course_id`='" + id + "'";
		SqlHelper helper = new SqlHelper();
		CachedRowSet dateData = new CachedRowSetImpl();
		String result = helper.excuteSql(sqlString, dateData);
		while (dateData.next()) {
			course.addDate(dateData.getString("date"));
		}
		dateData.close();
		return result;
	}

	private String getCourseFromCcAddress(Course course, String id) throws SQLException {
		String sqlString = "SELECT cc_email FROM `course_has_cc_address` WHERE `fk_course_id`='" + id + "'";
		SqlHelper helper = new SqlHelper();
		CachedRowSet ccData = new CachedRowSetImpl();
		String result = helper.excuteSql(sqlString, ccData);
		while (ccData.next()) {
			course.addCcAddresses(ccData.getString("cc_email"));
		}
		ccData.close();
		return result;
	}

	public String addCourseIntoDatabase(Course course) throws SQLException {
		String result = "";
		result = addCourseIntoInfo(course);
		System.out.println("addCourseIntoInfo");
		System.out.println(result);
		if (result != "Success")
			return result;
		result = addCourseIntoDate(course);
		System.out.println("addCourseIntoDate");
		System.out.println(result);
		if (result != "Success")
			return result;
		result = addCourseIntoTicket(course);
		System.out.println("addCourseIntoTicket");
		System.out.println(result);
		if (result != "Success")
			return result;
		result = addCourseIntoCcAddress(course);
		System.out.println("addCourseIntoCcAddress");
		System.out.println(result);
		if (result != "Success")
			return result;
		databaseDataIdMax_++;
		return "Success";
	}

	private String addCourseIntoInfo(Course course) throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "INSERT INTO `slm2016`.`course_info` (`id`, `name`, `type`, `batch`, `duration`, `location`, `lecturer`, `fk_status_id`, `page_link`) VALUES (";
		if (course.getCourseId() != null) {
			sqlString += "'" + course.getCourseId() + "', '";
		} else {
			sqlString += "'teddysoftware-course-01-" + databaseDataIdMax_ + "', '";
		}
		sqlString += course.getCourseName() + "', '";
		sqlString += course.getType() + "', '";
		sqlString += course.getBatch() + "', ";
		sqlString += course.getDuration() + ", '";
		sqlString += course.getLocation() + "', '";
		sqlString += course.getLecturer() + "', '";
		for (int i = 0; i < courseStatus_.size(); i++) {
			if (courseStatus_.get(i).getValue().equals(course.getStatus())) {
				sqlString += courseStatus_.get(i).getKey() + "', '";
				break;
			}
		}
		sqlString += course.getHyperlink() + "');";
		// System.out.println(sqlString);
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		data.close();
		if (result != "Success")
			return result;
		return "Success";
	}

	private String addCourseIntoDate(Course course) throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "";
		List<String> dates = course.getDates();
		for (int i = 0; i < dates.size(); i++) {
			sqlString = "INSERT INTO `slm2016`.`course_has_date` (`fk_course_id`, `date`) VALUES (";
			if (course.getCourseId() != null) {
				sqlString += "'" + course.getCourseId() + "', '";
			} else {
				sqlString += "'teddysoftware-course-01-" + databaseDataIdMax_ + "', '";
			}
			sqlString += dates.get(i) + "');";
			// System.out.println(sqlString);
			CachedRowSet data = new CachedRowSetImpl();
			result = helper.excuteSql(sqlString, data);
			data.close();
			if (result != "Success")
				return result;
		}
		return "Success";
	}

	private String addCourseIntoTicket(Course course) throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "";
		List<String> types = course.getTicketTypes();
		List<Integer> prices = course.getPrices();
		for (int i = 0; i < types.size(); i++) {
			sqlString = "INSERT INTO `slm2016`.`course_has_ticket` (`fk_course_id`, `type`, `price`) VALUES (";
			if (course.getCourseId() != null) {
				sqlString += "'" + course.getCourseId() + "', '";
			} else {
				sqlString += "'teddysoftware-course-01-" + databaseDataIdMax_ + "', '";
			}
			sqlString += types.get(i) + "', '";
			sqlString += prices.get(i) + "');";
			// System.out.println(sqlString);
			CachedRowSet data = new CachedRowSetImpl();
			result = helper.excuteSql(sqlString, data);
			data.close();
			if (result != "Success")
				return result;
		}
		return "Success";
	}

	private String addCourseIntoCcAddress(Course course) throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "";
		List<String> ccAddresses = course.getCcAddresses();
		for (int i = 0; i < ccAddresses.size(); i++) {
			sqlString = "INSERT INTO `slm2016`.`course_has_cc_address` (`fk_course_id`, `cc_email`) VALUES (";
			if (course.getCourseId() != null) {
				sqlString += "'" + course.getCourseId() + "', '";
			} else {
				sqlString += "'teddysoftware-course-01-" + databaseDataIdMax_ + "', '";
			}
			sqlString += ccAddresses.get(i) + "');";
			// System.out.println(sqlString);
			CachedRowSet data = new CachedRowSetImpl();
			result = helper.excuteSql(sqlString, data);
			data.close();
			if (result != "Success")
				return result;
		}
		return "Success";
	}

	public String deleteCourseFromDatabase(String id) throws SQLException {
		System.out.println(System.currentTimeMillis());
		String result = "";
		result = deleteCourseFromDatabaseCcAddress(id);
		System.out.println("deleteCourseFromDatabaseCcAddress");
		System.out.println(result);
		if (result != "Success")
			return result;
		result = deleteCourseFromDatabaseDate(id);
		System.out.println("deleteCourseFromDatabaseDate");
		System.out.println(result);
		if (result != "Success")
			return result;
		result = deleteCourseFromDatabaseTicket(id);
		System.out.println("deleteCourseFromDatabaseTicket");
		System.out.println(result);
		if (result != "Success")
			return result;
		result = deleteCourseFromDatabaseInfo(id);
		System.out.println("deleteCourseFromDatabaseInfo");
		System.out.println(result);
		if (result != "Success")
			return result;
		System.out.println(System.currentTimeMillis());
		return "Success";
	}

	private String deleteCourseFromDatabaseCcAddress(String id) throws SQLException {
		System.out.println(id);
		SqlHelper helper = new SqlHelper();
		String result = "";
		String sqlString = "DELETE FROM `course_has_cc_address` WHERE `fk_course_id`=";
		sqlString += "'" + id + "'";
		System.out.println(sqlString);
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		data.close();
		return result;
	}

	private String deleteCourseFromDatabaseDate(String id) throws SQLException {
		SqlHelper helper = new SqlHelper();
		String result = "";
		String sqlString = "DELETE FROM `course_has_date` WHERE `fk_course_id`=";
		sqlString += "'" + id + "'";
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		data.close();
		return result;
	}

	private String deleteCourseFromDatabaseTicket(String id) throws SQLException {
		SqlHelper helper = new SqlHelper();
		String result = "";
		String sqlString = "DELETE FROM `course_has_ticket` WHERE `fk_course_id`=";
		sqlString += "'" + id + "'";
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		data.close();
		return result;
	}

	private String deleteCourseFromDatabaseInfo(String id) throws SQLException {
		SqlHelper helper = new SqlHelper();
		String result = "";
		String sqlString = "DELETE FROM `course_info` WHERE `id`=";
		sqlString += "'" + id + "'";
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		data.close();
		return result;
	}
}
