package unitTests;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import mailSending.GmailSender;

public class GmailSenderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendSuccess() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "superchobits02@gmail.com";
		String subject = "泰迪軟體課程通知";
		String name = "Alice";
		String className = "Scurm敏捷發法實作班";
		String result = gmailSender.Send(address, subject, name, className);

		assertEquals("寄送email結束.", result);
	}

	@Test
	public void testAccountLoginFail() {
		String username = "000111222333@gmail.com";
		String password = "123456";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "superchobits02@gmail.com";
		String subject = "泰迪軟體課程通知";
		String name = "Alice";
		String className = "Scurm敏捷發法實作班";
		String result = gmailSender.Send(address, subject, name, className);

		assertEquals("帳號密碼不正確", result);
	}

	@Test
	public void testAccountLessSecureApps() {
		String username = "t100590334@ntut.org.tw";
		String password = "angelbeats711529";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "angelbeats711529@gmail.com";
		String subject = "泰迪軟體課程通知";
		String name = "Alice";
		String className = "Scurm敏捷發法實作班";
		String result = gmailSender.Send(address, subject, name, className);

		assertEquals("請開啟    安全性較低的應用程式存取權限", result);
	}

	@Test
	public void testRecipientAddressFormatError() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "0000aaaaa@gmail,com";
		String subject = "泰迪軟體課程通知";
		String name = "Alice";
		String className = "Scurm敏捷發法實作班";
		String result = gmailSender.Send(address, subject, name, className);

		assertEquals("送信位址格式不正確", result);
	}

	@Test
	public void testSendFirewallFail() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "superchobits02@gmail.com";
		String subject = "泰迪軟體課程通知";
		String name = "Alice";
		String className = "Scurm敏捷發法實作班";
		String result = gmailSender.Send(address, subject, name, className);

		assertNotEquals("無法連線到SMTP host，請檢察防火牆或Proxy設定", result);
	}

	@Test
	public void testSendNetwordFail() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "superchobits02@gmail.com";
		String subject = "泰迪軟體課程通知";
		String name = "Alice";
		String className = "Scurm敏捷發法實作班";
		String result = gmailSender.Send(address, subject, name, className);

		assertNotEquals("Unknown SMTP host: smtp.gmail.com，請檢察網路連線", result);
	}
}
