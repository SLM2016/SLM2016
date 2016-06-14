package servlets;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import sun.misc.BASE64Encoder;
import javax.sql.rowset.CachedRowSet;
import org.apache.poi.util.IOUtils;
import com.sun.rowset.CachedRowSetImpl;
import util.SqlHelper;

@WebServlet("/UpdateCertificationBackgroundServlet")
@MultipartConfig()
public class UpdateCertificationBackgroundServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static Gson gson = new Gson();
	public UpdateCertificationBackgroundServlet() {
		super();
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		HashMap<String, String> result = new HashMap<String, String>();
		PrintWriter out = response.getWriter();
		String courseId = request.getParameter("courseId");		
		String folderPath = getServletContext().getRealPath("images/").toString();
		folderPath = folderPath.substring(0, folderPath.length()-15);
		String dirPath = String.format((folderPath+"%s.jpg"),courseId);
		dirPath=dirPath.replace("\\","//");
		System.out.println("dirPath:"+dirPath);
		Part filePart = request.getPart("file");
		String sqlresult="";
		if(filePart!=null){
		try {
			OutputStream fileOut = new FileOutputStream(dirPath);
			IOUtils.copy(filePart.getInputStream(), fileOut);
			fileOut.close();
			String sqlString = String.format("UPDATE `course_info` SET certificationPath = '%s' WHERE id = '%s'",dirPath ,courseId);
			SqlHelper helper = new SqlHelper();
			try {
				CachedRowSet certificationPath = new CachedRowSetImpl();
				sqlresult = helper.excuteSql(sqlString, certificationPath);
				certificationPath.close();
				System.out.println(sqlString);
				if(sqlresult== "Success")
					result.put("status", "true");
				else
					result.put("status", "SQL fail");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("status", "DBfalse");
			}
		} catch (IOException ex) {
			System.out.println(ex);
			result.put("status", "false");
		}
		}
		else
			result.put("status", "false");
		out.println(gson.toJson(result));
	}
}
