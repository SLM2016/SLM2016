package student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.poi.util.IOUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnsignedDecimalNumber;

import com.google.gson.Gson;

import util.SLMDBUtility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/StudentAction")
@MultipartConfig()
public class StudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String OP_INSERT_INTO_STUDENT = "1";
	private static final String OP_GET_STUDENT_LIST = "2";
	private static final String OP_INSERT_FROM_GOOGLE_FORM = "3";
	private static final String OP_SAVE_STUDENT_EXCEL_FILE = "4";
	private static final String OP_GET_STUDENT_LIST_BY_COURSE_ID = "5";

	public StudentAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("op");
		switch (op) {
		case OP_GET_STUDENT_LIST:
			getStudentList(request, response);
			break;
		case OP_GET_STUDENT_LIST_BY_COURSE_ID:
			getStudentListByCourseId(request, response);
		default:
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		// PrintWriter out = response.getWriter();
		switch (op) {
		case OP_INSERT_INTO_STUDENT:
			insertIntoStudent(request, response);
			// SELECT * FROM `student_info`
			// StudentDBManager s = new StudentDBManager();
			// SLMDBUtility s = new SLMDBUtility();
			// s.selectSQL("SELECT * FROM `student_info` ");
			break;
		case OP_SAVE_STUDENT_EXCEL_FILE:
			saveFile(request, response);
			break;
		case OP_INSERT_FROM_GOOGLE_FORM:
			break;
		default:
			break;
		}

		// Gson gson = new Gson();
		// out.println(gson.toJson(result));
	}

	private void getStudentList(HttpServletRequest request, HttpServletResponse response) {
		StudentDBManager studentDbManager = new StudentDBManager();
		String json = null;

		try {
			json = studentDbManager.getStudentList();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void insertIntoStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		// final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		HashMap<String, String> result = new HashMap<String, String>();
		// Calendar cal = Calendar.getInstance();
		// SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

		String courseId = request.getParameter("courseId");
		Part filePart1 = request.getPart("file");

		// courseId = "ZZZZZTEST";
		// Workbook workbook = new XSSFWorkbook(filePart1.getInputStream());

		try {
			StudentModelFactory factory = new StudentModelFactory(filePart1.getInputStream(), courseId);

			ArrayList<StudentModel> arr = factory.buildStudentModelArray();
			StudentDBManager studentDbManager = new StudentDBManager();
			for (StudentModel s : arr) {
				System.out.println(s.getComment());
				studentDbManager.insertStudent(s);
			}

			// StudentModel s = factory.buildStudentModel(2);

			// StudentDBManager studentDbManager = new StudentDBManager();
			// boolean dbResult = studentDbManager.insertStudent(name,
			// email, nickname, phone, company, apartment,
			// title, ticketType, vegeMeat, receiptType, companyNameAndEIN,
			// classInfo, hasScrum, flowOk,
			// teamMembers, comment, timestamp);
			// if (dbResult) {
			// result.put("status", true);
			// } else {
			// result.put("status", false);
			// }
			// }
			result.put("status", "true");
		} catch (Exception e) {
			// e.printStackTrace();
			result.put("status", "false");
		}

		Gson gson = new Gson();
		out.println(gson.toJson(result));
		// return result;
	}

	private void saveFile(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		HashMap<String, String> result = new HashMap<String, String>();

		Date date = new java.util.Date();
		String nowTime = Long.toString(date.getTime());
		String fileName = String.format("%s.xlsx", nowTime);

		String courseName = request.getParameter("courseName");
		// courseName = "TEST";
		String dirPath = String.format("./course_excel_file/%s/", courseName);
		Part filePart1 = request.getPart("file");

		File f = new File(dirPath);
		if (!f.exists()) {
			f.mkdirs();
		}

		// System.out.println(System.getProperty("user.dir"));
		try {
			OutputStream out = new FileOutputStream(dirPath + fileName);
			IOUtils.copy(filePart1.getInputStream(), out);
			out.close();

		} catch (IOException ex) {
			// report
		}

	}

	private void getStudentListByCourseId(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String courseId = request.getParameter("courseId");

		StudentDBManager studentDbManager = new StudentDBManager();
		String json = null;

		try {
			json = studentDbManager.getStudentListByCourseId(courseId);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
