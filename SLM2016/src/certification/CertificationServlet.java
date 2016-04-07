package certification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CertificationServlet")
public class CertificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CertificationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// prepare template and personal data first
		CertificationMaker certificationMaker = new CertificationMaker();
		List<Certification> certificationList = new ArrayList<Certification>();
		certificationList.add(certificationMaker.MakeCertification("", ""));
		certificationList.add(certificationMaker.MakeCertification("", ""));
		certificationList.add(certificationMaker.MakeCertification("", ""));

		// Only for test, write stable data
		certificationList.get(0).setOwner("廖振甫");
		certificationList.get(0).setId("SCRUM1603-33");
		certificationList.get(1).setOwner("陳泰迪");
		certificationList.get(1).setId("SCRUM1603-32");
		certificationList.get(2).setOwner("帥哥");
		certificationList.get(2).setId("SCRUM1603-31");

		TemplateCertificationMaker templateCertificationMaker = new TemplateCertificationMaker();
		TemplateCertification template = templateCertificationMaker.MakeTemplateCertification("");

		// request certification of No.x element in certificationList
		// response for a json which content state, imageString
		int order = 1;
		CertificationManager manager = new CertificationManager();
		manager.makeCertification(template, certificationList.get(order),
				getServletContext().getRealPath("images/template.png").toString());
		response.setContentType("image//png");
		response.setHeader("Content-Disposition", "inline; fileName=templateA.png");
		/*
		 * response.setContentType("application/json");
		 * response.setCharacterEncoding("UTF-8");
		 */
		response.getWriter().write(manager.getCertificationJsonString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
	}
}
