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

@WebServlet("/CourseManagerServlet")
public class CourseManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private List<Course> courses_ = new ArrayList<Course>();
	private CourseManagerWithDatabase courseManagerWithDb_ = new CourseManagerWithDatabase();

	public CourseManagerServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		// System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestString = request.getReader().readLine();
		System.out.println(requestString);
		String header = request.getHeader("Delete");
		if (header != null) {
			doPostDeleteCourse(request, response, requestString);
			return;
		}
		doPostAddCourse(request, response, requestString);
	}

	private void doPostDeleteCourse(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		String id = requestString;
		String result = "";
		try {
			result = courseManagerWithDb_.deleteCourseFromDatabase(id);
		} catch (SQLException e) {

		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}

	private void doPostAddCourse(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		Gson gson = new Gson();
		Course course = gson.fromJson(requestString, Course.class);
		// courses_.add(course);
		String result = "success";
		try {
			result = courseManagerWithDb_.addCourseIntoDatabase(course);
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
}
