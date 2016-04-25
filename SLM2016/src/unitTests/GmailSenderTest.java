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
		String address = "t104598007@ntut.org.tw";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件";
		String ccAddresses = "superchobits02@gmail.com";
		String result = gmailSender.send(address, ccAddresses, subject, text);

		assertEquals("寄送email結束.", result);
	}

	@Test
	public void testAccountLoginFail() {
		String username = "000111222333@gmail.com";
		String password = "123456";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "t104598007@ntut.org.tw";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件";
		String ccAddresses = "";
		String result = gmailSender.send(address, ccAddresses, subject, text);

		assertEquals("帳號密碼不正確", result);
	}

	@Test
	public void testAccountLessSecureApps() {
		String username = "t100590334@ntut.org.tw";
		String password = "angelbeats711529";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "t104598007@ntut.org.tw";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件";
		String ccAddresses = "";
		String result = gmailSender.send(address, ccAddresses, subject, text);

		assertEquals("請開啟    安全性較低的應用程式存取權限", result);
	}

	@Test
	public void testRecipientAddressFormatError() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "0000aaaaa@gmail,com";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件";
		String ccAddresses = "";
		String result = gmailSender.send(address, ccAddresses, subject, text);

		assertEquals("送信位址格式不正確", result);
	}

	@Test
	public void testSendFirewallFail() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "t104598007@ntut.org.tw";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件";
		String ccAddresses = "";
		String result = gmailSender.send(address, ccAddresses, subject, text);

		assertNotEquals("無法連線到SMTP host，請檢察防火牆或Proxy設定", result);
	}

	@Test
	public void testSendNetwordFail() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "SLMTaipeiTech2016";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "t104598007@ntut.org.tw";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件";
		String ccAddresses = "";
		String result = gmailSender.send(address, ccAddresses, subject, text);

		assertNotEquals("Unknown SMTP host: smtp.gmail.com，請檢察網路連線", result);
	}
}
