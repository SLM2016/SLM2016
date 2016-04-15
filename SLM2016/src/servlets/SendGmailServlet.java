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
import mailSending.SendStudentIndex;
import mailSending.StudentInfomation;

@WebServlet("/SendGmailServlet")
public class SendGmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentInfomation studentInfomation = new StudentInfomation();
	private ClassInfomation classInfomation = new ClassInfomation("Scurm敏捷發法實作班");

	public SendGmailServlet() {
		super();
		studentInfomation.SetStudents();
		studentInfomation.SetMailAddress();
		
		List<String> name = studentInfomation.GetStudents();
		int size = 30;
		for (int i = 0; i < size; i++) {
			classInfomation.AddStudent(name.get(i));
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		String json = new Gson().toJson(classInfomation);
		// System.out.println(json);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		SendStudentIndex sendStudentIndex = gson.fromJson(request.getReader().readLine(), SendStudentIndex.class);
		List<Integer> studentIndex = sendStudentIndex.GetIndexes();
		List<String> studentName = new ArrayList<String>();
		List<String> addresses = new ArrayList<String>();
		for (int i = 0; i < studentIndex.size(); i++)
			if (i == studentIndex.get(i).intValue()) {
				studentName.add(studentInfomation.GetStudents().get(i));
				addresses.add(studentInfomation.GetMailAddress().get(i));
			}
		
		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender=new GmailSender(username,password);
		String subject = "泰迪軟體課程通知";
		String result="";
		for(int i=0;i<studentName.size();i++)
			result = gmailSender.Send(addresses.get(i),subject,studentName.get(i),classInfomation.GetClassName());
		
		String json = new Gson().toJson(result);
		System.out.println(result);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
