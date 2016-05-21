package student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.poi.util.IOUtils;
import com.google.gson.Gson;

import courseManager.Course;
import courseManager.CourseManagerWithDatabase;

import java.io.StringReader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.jspsmart.upload.SmartUpload;

@WebServlet("/StudentAction")
@MultipartConfig()
public class StudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String OP_INSERT_INTO_STUDENT = "1";
	private static final String OP_GET_STUDENT_LIST = "2";
	private static final String OP_INSERT_STUDENT_FROM_GOOGLE_FORM = "3";
	private static final String OP_SAVE_STUDENT_EXCEL_FILE = "4";
	private static final String OP_GET_STUDENT_LIST_BY_COURSE_ID = "5";
	private static final String OP_UPDATE_STUDENT_RECEIPT_STATUS = "6";
	private static final String OP_GET_SEND_MAIL_INFO = "7";
	private static final String OP_GET_CERTIFICATION_INFO = "8";


	private static Gson gson = new Gson();

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
			break;
		case OP_GET_SEND_MAIL_INFO:
			getSendMailInfo(request, response);
			break;
		case OP_GET_CERTIFICATION_INFO:
			getCertificationInfo(request, response);
			break;
		default:
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String op = request.getParameter("op");
		// PrintWriter out = response.getWriter();
		switch (op) {
		case OP_INSERT_INTO_STUDENT:
			insertIntoStudent(request, response);
			break;
		case OP_SAVE_STUDENT_EXCEL_FILE:
			saveFile(request, response);
			break;
		case OP_INSERT_STUDENT_FROM_GOOGLE_FORM:
			break;
		case OP_UPDATE_STUDENT_RECEIPT_STATUS:
			updateStudentReceiptStatus(request, response);
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
	
	private void getSendMailInfo(HttpServletRequest request, HttpServletResponse response) {
		GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        StudentSendMailData[] studentSendMailData = gson.fromJson(request.getParameter("mailData"), StudentSendMailData[].class);
        
		StudentDBManager studentDbManager = new StudentDBManager();
		String result = null;
					
		try {
			result = studentDbManager.getSendMailInfo(studentSendMailData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getCertificationInfo(HttpServletRequest request, HttpServletResponse response) {      
		StudentDBManager studentDbManager = new StudentDBManager();
		String result = null;
					
		try {
			result = studentDbManager.getCertificationInfo(request.getParameter("studentId"));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void insertIntoStudent(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		HashMap<String, String> result = new HashMap<String, String>();
		// Calendar cal = Calendar.getInstance();
		// SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

		String courseId = request.getParameter("courseId");
		Part filePart = request.getPart("file");

		try {
			StudentModelFactory factory = new StudentModelFactory(filePart.getInputStream(), courseId);

			ArrayList<StudentModel> arr = factory.buildStudentModelArray();
				StudentDBManager studentDbManager = new StudentDBManager();
			for (StudentModel s : arr) {
				// System.out.println(s.getComment());
				studentDbManager.insertStudent(s);
				}

			result.put("status", "true");
		} catch (Exception e) {
			// e.printStackTrace();
			result.put("status", "false");
		}

		out.println(gson.toJson(result));
		// return result;
	}

	private void updateStudentReceiptStatus(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		HashMap<String, String> result = new HashMap<String, String>();

		try {

			StudentModel studentModel;
			StudentDBManager studentDBManager = new StudentDBManager();
			String studentID = request.getParameter("studentId");
			String receiptEIN = request.getParameter("receiptEIN");
			String receiptStatus = request.getParameter("receiptStatus");
			String paymentStatus = request.getParameter("paymentStatus");

			studentModel = studentDBManager.getStudentById(studentID);
			studentModel.setReceiptEIN(receiptEIN);
			studentModel.setReceiptStatus(receiptStatus);
			studentModel.setPaymentStatus(paymentStatus);

			if (studentDBManager.updateStudent(studentModel)) {
				result.put("status", "true");
			} else {

				result.put("status", "false");
			}

		} catch (Exception e) {
			result.put("status", "false");
			e.printStackTrace();
		}

		out.println(gson.toJson(result));
	}
	
	private void saveFile(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HashMap<String, String> result = new HashMap<String, String>();
		PrintWriter out = response.getWriter();

		Date date = new java.util.Date();
		String nowTime = Long.toString(date.getTime());
		String fileName = String.format("%s.xlsx", nowTime);

		String courseId = request.getParameter("courseId");
		// courseName = "TEST";
		String dirPath = String.format("./course_excel_file/%s/", courseId);
		Part filePart = request.getPart("file");
		try {
			File f = new File(dirPath);
			if (!f.exists()) {
				f.mkdirs();
			}
			// System.out.println(f.getAbsolutePath());
			OutputStream fileOut = new FileOutputStream(dirPath + fileName);
			IOUtils.copy(filePart.getInputStream(), fileOut);
			fileOut.close();
			result.put("status", "true");

		} catch (

		IOException ex) {
			result.put("status", "false");
		}

		out.println(gson.toJson(result));

	}

	private void getStudentListByCourseId(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
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

	private void insertStudentFromGoogleForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		// CourseManagerWithDatabase courseDB = new CourseManagerWithDatabase();
		// System.out.println(courseDB.getCourseId("TTTT"));

	}

}
