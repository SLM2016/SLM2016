package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dataClass.Course;

@WebServlet("/CourseManagerServlet")
public class CourseManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Course> courses_ = new ArrayList<Course>();

	public CourseManagerServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = new Gson().toJson(courses_);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestString = request.getReader().readLine();
		Gson gson = new Gson();
		Course course = gson.fromJson(requestString, Course.class);
		courses_.add(course);
		String json = new Gson().toJson("success");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
