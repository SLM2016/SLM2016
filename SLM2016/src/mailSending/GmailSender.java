package mailSending;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailSender {
	private final String host_ = "smtp.gmail.com";
	private final int port_ = 587;
	private String username_;
	private String password_;
	private Properties props_ = new Properties();
	private Session session_;

	public GmailSender(String username, String password) {
		username_ = username;
		password_ = password;
		System.setProperty("mail.mime.charset", "big5");
		props_.put("mail.smtp.host", host_);
		props_.put("mail.smtp.auth", "true");
		props_.put("mail.smtp.starttls.enable", "true");
		props_.put("mail.smtp.port", port_);
		session_ = Session.getInstance(props_, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public String send(String address, String subject, String name, String className) {
		try {
			Message message = new MimeMessage(session_);
			message.setFrom(new InternetAddress(username_));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
			message.setSubject(subject);
			String text = "Hi " + name + "，\n您好，歡迎報名" + className + "，以下是您的上課通知，請參考。\n若有任何問題，歡迎隨時聯絡我們。\n泰迪軟體 Erica";
			message.setText(text);

			Transport transport = session_.getTransport("smtp");
			transport.connect(host_, port_, username_, password_);
			Transport.send(message);

			return "寄送email結束.";

		} catch (MessagingException e) {
			String errorMessage = e.getMessage().toString();
			if (errorMessage.contains("https://accounts.google.com/signin/continue?")) {
				return "請開啟    安全性較低的應用程式存取權限";
			} 
			else if (errorMessage.contains("Username and Password not accepted")) {
				return "帳號密碼不正確";
			} 
			else if (errorMessage.contains("Invalid Addresses")) {
				return "送信位址格式不正確";
			} 
			else if (errorMessage.contains("Could not connect to SMTP host: smtp.gmail.com")) {
				return "無法連線到SMTP host，請檢察防火牆或Proxy設定";
			} 
			else if (errorMessage.contains("Unknown SMTP host: smtp.gmail.com")) {
				return "Unknown SMTP host: smtp.gmail.com，請檢察網路連線";
			} 
			else {
				return e.getMessage().toString();
				// "Could not connect to SMTP host: smtp.gmail.com, port: 465, response: -1"
				// Unknown SMTP host: smtp.gmail.com
			}
		}
	}
}
