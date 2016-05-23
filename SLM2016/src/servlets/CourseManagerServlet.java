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
import com.google.gson.Gson;
import courseManager.Course;
import courseManager.CourseManagerWithDatabase;
import student.StudentDBManager;

@WebServlet("/CourseManagerServlet")
public class CourseManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CourseManagerWithDatabase courseManagerWithDb_ = new CourseManagerWithDatabase();

	public CourseManagerServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String simpleData = request.getHeader("simpleData");
		if (simpleData != null) {
			getCourseSimpleData(request, response);
		} else {
			List<Course> courses_ = new ArrayList<Course>();
			String result = "";
			try {
				result = courseManagerWithDb_.getCourseFromDatabase(courses_);
			} catch (SQLException e) {

			}
			String json;
			if (result != "Success") {
				json = new Gson().toJson(result);
			} else {
				json = new Gson().toJson(courses_);
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}

	private void getCourseSimpleData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Course> courses_ = new ArrayList<Course>();
		String result = "";
		try {
			result = courseManagerWithDb_.getCourseSimpleDataFromDatabase(courses_);
		} catch (SQLException e) {

		}
		String json;
		if (result != "Success") {
			json = new Gson().toJson(result);
		} else {
			json = new Gson().toJson(courses_);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestString = request.getReader().readLine();
		String header = request.getHeader("Delete");
		if (header != null) {
			doPostDeleteCourse(request, response, requestString);
		} else {
			doPostAddCourse(request, response, requestString);
		}
	}

	private void doPostDeleteCourse(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		String id = requestString;
		String result = "";
		try {
			result = courseManagerWithDb_.deleteCourseFromDatabase(id);
			StudentDBManager studentDBManager = new StudentDBManager();
			studentDBManager.deleteStudentsByCourseId(id);
		} catch (SQLException e) {

		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}

	private void doPostAddCourse(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		Course course = gson.fromJson(requestString, Course.class);
		String result = "success";
		try {
			if (courseManagerWithDb_.getCourseIdByCourseNameAndBatchAndStatus(course.getCourseName(), course.getBatch(),
					course.getStatus()) != null) {
				result = "已存在相同課程";
			}
			result = courseManagerWithDb_.addCourseIntoDatabase(course);
		} catch (SQLException e) {

		}
		String json = new Gson().toJson(result);
		response.getWriter().write(json);
	}
}
