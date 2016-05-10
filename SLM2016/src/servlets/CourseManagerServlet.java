package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;

import courseManager.Course;
import javafx.util.Pair;
import util.SqlHelper;

@WebServlet("/CourseManagerServlet")
public class CourseManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private List<Course> courses_ = new ArrayList<Course>();
	private int dbDataIdMax_;
	private List<Pair<String, String>> courseStatus_ = new ArrayList<Pair<String, String>>();

	public CourseManagerServlet() {
		super();
		dbDataIdMax_ = 1;
		try {
			getCourseStatusTable();
			getAllCourseId();
		} catch (SQLException e) {

		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Course> courses_ = new ArrayList<Course>();
		String result = "";
		try {
			result = getCourseFromDb(courses_);
		} catch (SQLException e) {

		}
		String json;
		if (result != "Success") {
			json = new Gson().toJson(result);
		} else {
			json = new Gson().toJson(courses_);
		}
		// System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestString = request.getReader().readLine();
		// System.out.println(requestString);
		String header = request.getHeader("Delete");
		if (header != null) {
			doPostDeleteCourse(request, response, requestString);
			return;
		}
		doPostAddCourse(request, response, requestString);
	}

	private void doPostDeleteCourse(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		// int index = Integer.valueOf(requestString);
		// String result = "";
		// if (index < courses_.size() && index >= 0) {
		// courses_.remove(index);
		// result = "success";
		// } else {
		// result = "fail";
		// }
		// response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		// response.getWriter().write(result);
	}

	private void doPostAddCourse(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		Gson gson = new Gson();
		Course course = gson.fromJson(requestString, Course.class);
		// courses_.add(course);
		String result = "success";
		try {
			result = AddCourseIntoDatabase(course);
		} catch (SQLException e) {

		}
		String json = new Gson().toJson(result);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		// System.out.println("course");
		// for (int i = 0; i < courses_.size(); i++) {
		// System.out.println(course.getCourseName());
		// System.out.println(course.getBatch());
		// System.out.println(course.getDate());
		// System.out.println(course.getDuration());
		// System.out.println(course.getTicketTypes());
		// System.out.println(course.getPrices());
		// System.out.println(course.getLocation());
		// System.out.println(course.getLecturer());
		// System.out.println(course.getStatus());
		// }
		//
		// System.out.println("DATA");
		// for (int i = 0; i < courses_.size(); i++) {
		// System.out.println(courses_.get(i).getCourseName());
		// System.out.println(courses_.get(i).getBatch());
		// System.out.println(courses_.get(i).getDate());
		// System.out.println(courses_.get(i).getDuration());
		// System.out.println(courses_.get(i).getTicketTypes());
		// System.out.println(courses_.get(i).getPrices());
		// System.out.println(courses_.get(i).getLocation());
		// System.out.println(courses_.get(i).getLecturer());
		// System.out.println(courses_.get(i).getStatus());
		// }
	}

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
			id = id.substring(index + 1);
			dbDataIdMax_ = Integer.parseInt(id);
			dbDataIdMax_++;
			// System.out.println(dbDataIdMax_);
		}
		return result;
	}

	private String getCourseFromDb(List<Course> courses) throws SQLException {
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

	private String AddCourseIntoDatabase(Course course) throws SQLException {
		String result = "";
		result = AddCourseIntoInfo(course);
		if (result != "Success")
			return result;
		result = AddCourseIntoDate(course);
		if (result != "Success")
			return result;
		result = AddCourseIntoTicket(course);
		if (result != "Success")
			return result;
		result = AddCourseIntoCcAddress(course);
		if (result != "Success")
			return result;
		dbDataIdMax_++;
		return "Success";
	}

	private String AddCourseIntoInfo(Course course) throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "INSERT INTO `slm2016`.`course_info` (`id`, `name`, `type`, `batch`, `duration`, `location`, `lecturer`, `fk_status_id`, `page_link`) VALUES (";
		sqlString += "'teddysoftware-course-01-" + dbDataIdMax_ + "', '";
		sqlString += course.getCourseName() + "', '";
		sqlString += course.getType() + "', '";
		sqlString += course.getBatch() + "',";
		sqlString += course.getDuration() + ", '";
		sqlString += course.getLocation() + "', '";
		sqlString += course.getLecturer() + "', '";
		for (int i = 0; i < courseStatus_.size(); i++) {
			if (courseStatus_.get(i).getValue() == course.getStatus()) {
				sqlString += courseStatus_.get(i).getKey() + "', '";
			}
		}
		sqlString += course.getHyperlink() + "');";
		// System.out.println(sqlString);
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		if (result != "Success")
			return result;
		data.close();
		return "Success";
	}

	private String AddCourseIntoDate(Course course) throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "INSERT INTO `slm2016`.`course_has_date` (`fk_course_id`, `date`) VALUES (";
		List<String> dates = course.getDates();
		for (int i = 0; i < dates.size(); i++) {
			sqlString += "'teddysoftware-course-01-" + dbDataIdMax_ + "', '";
			sqlString += dates.get(i) + "');";
		}
		// System.out.println(sqlString);
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		if (result != "Success")
			return result;
		data.close();
		return "Success";
	}

	private String AddCourseIntoTicket(Course course) throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "INSERT INTO `slm2016`.`course_has_ticket` (`fk_course_id`, `type`, `price`) VALUES (";
		List<String> types = course.getTicketTypes();
		List<Integer> prices = course.getPrices();
		for (int i = 0; i < types.size(); i++) {
			sqlString += "'teddysoftware-course-01-" + dbDataIdMax_ + "', '";
			sqlString += types.get(i) + "', '";
			sqlString += prices.get(i) + "');";
		}
		// System.out.println(sqlString);
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		if (result != "Success")
			return result;
		data.close();
		return "Success";
	}

	private String AddCourseIntoCcAddress(Course course) throws SQLException {
		String result = "";
		SqlHelper helper = new SqlHelper();
		String sqlString = "INSERT INTO `slm2016`.`course_has_cc_address` (`fk_course_id`, `cc_email`) VALUES (";
		List<String> ccAddresses = course.getCcAddresses();
		for (int i = 0; i < ccAddresses.size(); i++) {
			sqlString += "'teddysoftware-course-01-" + dbDataIdMax_ + "', '";
			sqlString += ccAddresses.get(i) + "');";
		}
		// System.out.println(sqlString);
		CachedRowSet data = new CachedRowSetImpl();
		result = helper.excuteSql(sqlString, data);
		if (result != "Success")
			return result;
		data.close();
		return "Success";
	}
}
