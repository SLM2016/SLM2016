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

import dataClass.Classes;
import dataClass.StudentInfomation;
import mailSending.GmailSender;
import mailSending.StudentSelectedIndex;

@WebServlet("/SendGmailServlet")
public class SendGmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private StudentInfomation studentInfomation = new StudentInfomation();
	// private ClassInfomation classInfomation = new
	// ClassInfomation("Scurm敏捷方法實作班");
	private Classes classes_ = new Classes();

	public SendGmailServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = new Gson().toJson(classes_);
		// System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestString = request.getReader().readLine();
		String header = request.getHeader("isSend");
		if (header != null) {
			doPostClassIndex(request, response, requestString);
			return;
		}
		// if (request.getParameterMap().containsKey("isSend")) {
		// doPostClassIndex(request, response, requestString);
		// return;
		// }
		doPostSendMail(request, response, requestString);
	}

	private void doPostClassIndex(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		int index = Integer.valueOf(requestString);
		String json = new Gson().toJson(classes_.getClasses().get(index));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void doPostSendMail(HttpServletRequest request, HttpServletResponse response, String requestString)
			throws ServletException, IOException {
		Gson gson = new Gson();
		StudentSelectedIndex sendStudentIndex = gson.fromJson(requestString, StudentSelectedIndex.class);
		int index = sendStudentIndex.getClassIndex();
		String ccAddresses = sendStudentIndex.getCCAddresses();
		List<Integer> studentIndex = sendStudentIndex.getIndexes();
		List<String> studentName = new ArrayList<String>();
		List<String> addresses = new ArrayList<String>();
		StudentInfomation studentInfomation = classes_.getClasses().get(index).getStudentsInfomation();
		for (int i = 0; i < studentIndex.size(); i++) {
			for (int j = 0; j < studentInfomation.getStudents().size(); j++) {
				if (j == studentIndex.get(i).intValue()) {
					studentName.add(studentInfomation.getStudents().get(j));
					addresses.add(studentInfomation.getMailAddress().get(j));
				}
			}
		}

		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender = new GmailSender(username, password);
		String subject = "泰迪軟體課程通知";
		String result = "";
		for (int i = 0; i < studentName.size(); i++) {
			String text = "Hi " + studentName.get(i) + "，\n您好，歡迎報名" + classes_.getClasses().get(index).getClassName()
					+ "，以下是您的上課通知，請參考。\n若有任何問題，歡迎隨時聯絡我們。\n泰迪軟體 Erica";
			result = gmailSender.send(addresses.get(i), ccAddresses, subject, text);
		}
		String json = new Gson().toJson(result);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
