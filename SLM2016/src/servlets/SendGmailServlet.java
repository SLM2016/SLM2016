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

import mailSending.ClassInfomation;
import mailSending.GmailSender;
import mailSending.StudentSelectedIndex;
import mailSending.StudentInfomation;

@WebServlet("/SendGmailServlet")
public class SendGmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentInfomation studentInfomation = new StudentInfomation();
	private ClassInfomation classInfomation = new ClassInfomation("Scurm敏捷發法實作班");

	public SendGmailServlet() {
		super();
		studentInfomation.setStudents();
		studentInfomation.setMailAddress();

		List<String> name = studentInfomation.getStudents();
		int size = 30;
		for (int i = 0; i < size; i++) {
			classInfomation.addStudent(name.get(i));
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = new Gson().toJson(classInfomation);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		StudentSelectedIndex sendStudentIndex = gson.fromJson(request.getReader().readLine(),
				StudentSelectedIndex.class);
		List<Integer> studentIndex = sendStudentIndex.getIndexes();
		List<String> studentName = new ArrayList<String>();
		List<String> addresses = new ArrayList<String>();
		for (int i = 0; i < studentIndex.size(); i++) {
			for (int j = 0; j < studentInfomation.getStudents().size(); j++) {
				if (j == studentIndex.get(i).intValue()) {
					studentName.add(studentInfomation.getStudents().get(j));
					addresses.add(studentInfomation.getMailAddress().get(j));
					System.out.println(j);
				}
			}
		}
		
		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender = new GmailSender(username, password);
		String subject = "泰迪軟體課程通知";
		String result = "";
		for (int i = 0; i < studentName.size(); i++)
			result = gmailSender.send(addresses.get(i), subject, studentName.get(i), classInfomation.getClassName());

		String json = new Gson().toJson(result);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
