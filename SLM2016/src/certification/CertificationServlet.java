package certification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/CertificationServlet")
public class CertificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CertificationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson son=new Gson();
		Certification certification=son.fromJson(request.getReader().readLine(), Certification.class);
		TemplateCertificationMaker templateCertificationMaker = new TemplateCertificationMaker();
		TemplateCertification template = templateCertificationMaker.MakeTemplateCertification("");

		CertificationManager manager = new CertificationManager();
		manager.makeCertification(template, certification, getServletContext().getRealPath("images/template.png").toString());
		response.setContentType("image//png");
		response.setHeader("Content-Disposition", "inline; fileName=templateA.png");
		response.getWriter().write(manager.getCertificationJsonString());
	}
}
