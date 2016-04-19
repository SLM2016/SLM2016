package student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.jspsmart.upload.SmartUpload;

@WebServlet("/StudentAction")
@MultipartConfig()
public class StudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		HashMap result = new HashMap();
		PrintWriter out = response.getWriter();
		switch (op) {
		case "1":
			result = insertIntoStudent(request, response);
			break;
		default:
			break;
		}

		Gson gson = new Gson();
		out.println(gson.toJson(result));

	}

	private HashMap insertIntoStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		HashMap result = new HashMap();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

		Part filePart1 = request.getPart("file");
		Workbook workbook = new XSSFWorkbook(filePart1.getInputStream());

		Sheet firstSheet = workbook.getSheetAt(0);

		try {
			for (int i = 1; i < firstSheet.getPhysicalNumberOfRows(); i++) {
				Row row = firstSheet.getRow(i);
				if (row == null) {
					continue;
				}
				int cells = row.getPhysicalNumberOfCells();
				Cell cell = row.getCell(0);
				String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date(cell.getDateCellValue().getTime()));
				cell = row.getCell(1);
				String ticketType = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(2);
				String name = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(3);
				String nickname = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(4);
				String email = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(5);
				String phone = String.format("%010d", (int) cell.getNumericCellValue());
				cell = row.getCell(6);
				String company = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(7);
				String apartment = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(8);
				String title = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(9);
				String vegeMeat = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(10);
				String receiptType = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(11);
				String companyNameAndEIN = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(12);
				String classInfo = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(13);
				String hasScrum = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(14);
				String flowOk = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(15);
				String teamMembers = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(16);
				String comment = (cell == null ? "" : cell.getStringCellValue());
				cell = row.getCell(17);

				StudentDBManager studentDbManager = new StudentDBManager();
				boolean dbResult = studentDbManager.insertStudent(name, email, nickname, phone, company, apartment,
						title, ticketType, vegeMeat, receiptType, companyNameAndEIN, classInfo, hasScrum, flowOk,
						teamMembers, comment, timestamp);
				if (dbResult) {
					result.put("status", true);
				} else {
					result.put("status", false);
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			result.put("status", false);
		}
		return result;
	}

}
