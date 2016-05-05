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

import courseManager.Course;

@WebServlet("/CourseManagerServlet")
public class CourseManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Course> courses_ = new ArrayList<Course>();

	public CourseManagerServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = new Gson().toJson(courses_);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestString = request.getReader().readLine();
		//System.out.println(requestString);
		String header = request.getHeader("Delete");
		if (header != null) {
			doPostDeleteCourse(request, response, requestString);
			return;
		}
		doPostAddCourse(request, response, requestString);
	}

	private void doPostDeleteCourse(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		int index = Integer.valueOf(requestString);
		String result = "";
		if (index < courses_.size() && index >= 0) {
			courses_.remove(index);
			result = "success";
		} else {
			result = "fail";
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}

	private void doPostAddCourse(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		Gson gson = new Gson();
		Course course = gson.fromJson(requestString, Course.class);
		courses_.add(course);
		String json = new Gson().toJson("success");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
//		System.out.println("course");
//		 for (int i = 0; i < courses_.size(); i++) {
//		 System.out.println(course.getCourseName());
//		 System.out.println(course.getBatch());
//		 System.out.println(course.getDate());
//		 System.out.println(course.getDuration());
//		 System.out.println(course.getTicketTypes());
//		 System.out.println(course.getPrices());
//		 System.out.println(course.getLocation());
//		 System.out.println(course.getLecturer());
//		 System.out.println(course.getStatus());
//		 }
//		 
//		System.out.println("DATA");
//		 for (int i = 0; i < courses_.size(); i++) {
//		 System.out.println(courses_.get(i).getCourseName());
//		 System.out.println(courses_.get(i).getBatch());
//		 System.out.println(courses_.get(i).getDate());
//		 System.out.println(courses_.get(i).getDuration());
//		 System.out.println(courses_.get(i).getTicketTypes());
//		 System.out.println(courses_.get(i).getPrices());
//		 System.out.println(courses_.get(i).getLocation());
//		 System.out.println(courses_.get(i).getLecturer());
//		 System.out.println(courses_.get(i).getStatus());
//		 }
	}
}
