package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mailSending.GmailSender;

@WebServlet("/ApplySuccessfullyServlet")
public class ApplySuccessfullyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GmailSender gmailSender = null;

    public ApplySuccessfullyServlet() {
        super();
        String username = "news.teddysoft.tw@gmail.com";
		String password = "clfddzifoyfvvxqa";
		GmailSender gmailSender = new GmailSender(username, password);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String subject = classes_.getClasses().get(index).getClassName() + " - 已收到您的報名資料";
//		String result = "";
//		for (int i = 0; i < studentName.size(); i++) {
//			String text = "Hi " + studentName.get(i) + "，<br><br>您好，歡迎報名<a href=\"" + hyperlinks_.get(index) + "\">"
//					+ classes_.getClasses().get(index).getClassName()
//					+ "</a>，我們已收到您填寫的相關資料。<br><br>本梯次課程目前招生中，若達開課人數，我們會再通知您後續繳費的相關事宜，最遲於開課前十天通知。如果有任何問題，歡迎和我們聯絡 : ) <br><br><br>泰迪軟體 Erica<br><br>-- <br>泰迪軟體科技有限公司 <br>Teddysoft Technology<br>02-2311-6230<br> <a href=\"http://teddysoft.tw\">http://teddysoft.tw </a>";
//			result = gmailSender.send(addresses.get(i), ccAddresses, subject, text);
//		}
//		String json = new Gson().toJson(result);
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(json);
	}

}
