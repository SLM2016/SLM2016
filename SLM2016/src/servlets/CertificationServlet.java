package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import certification.Certification;
import certification.CertificationManager;

@WebServlet("/CertificationServlet")
public class CertificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CertificationManager manager;

	public CertificationServlet() {
		super();
		manager = new CertificationManager();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson son=new Gson();
		StringBuffer requestInfo = new StringBuffer();
		requestInfo.append(request.getReader().readLine());
				
		if(requestInfo.indexOf("imagePath_", 0) < 0){
			Certification certification=son.fromJson(requestInfo.toString(), Certification.class);
			
			manager.makeCertification(certification, getServletContext().getRealPath("images/template.png").toString());
			response.setContentType("image//png");
			response.setHeader("Content-Disposition", "inline; fileName=templateA.png");
			response.getWriter().write(manager.getCertificationJsonString());
		}
		else{
			manager.makeCertificationPDF();
			response.getWriter().write(manager.getCertificationPDFJsonString());
		}		
	}
}
