package unitTests;

import static org.junit.Assert.*;

import java.util.Base64;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import certification.Certification;
import certification.CertificationManager;
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
		String password = "clfddzifoyfvvxqa";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "t100310313@ntut.org.tw";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件\ntestSendSuccess";
		String ccAddresses = "t100310313@gmail.com";
		String result = gmailSender.send(address, ccAddresses, subject, text, null);

		assertEquals("寄送email結束.", result);
	}
	
	@Test
	public void testSendSuccessWithHyperlink() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "clfddzifoyfvvxqa";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "t100310313@ntut.org.tw";
		String subject = "泰迪軟體課程通知";
		String text = "<a href=\"http://www.ntut.edu.tw/bin/home.php\">測試信件</a>";
		String ccAddresses = "t100310313@gmail.com";
		String result = gmailSender.send(address, ccAddresses, subject, text, null);

		assertEquals("寄送email結束.", result);
	}
	
	@Test
	public void testSendSuccessWithAttachment() {
		CertificationManager manager_ = null;
		Base64.Decoder base64Decoder = Base64.getDecoder();
		manager_ = new CertificationManager();
		manager_.makeCertification(new Certification("SCRUM1603-33", "陳泰迪"," 於 2016 年 03 月 22、23 日","Scrum敏捷方法實作班","全期共十二小時研習期滿，特此證明","2016 年 3 月 23 日"), "WebContent\\images\\template.png");
		manager_.makeCertificationPDF();
		String username = "news.teddysoft.tw@gmail.com";
		String password = "clfddzifoyfvvxqa";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "t100310313@ntut.org.tw";
		String subject = "泰迪軟體課程通知";
		String text = "<a href=\"http://www.ntut.edu.tw/bin/home.php\">測試信件</a>";
		String ccAddresses = "t100310313@gmail.com";
		String result = gmailSender.send(address, ccAddresses, subject, text, base64Decoder.decode(manager_.getCertificationPDFJsonString()));

		assertEquals("寄送email結束.", result);
	}

	@Test
	public void testAccountLoginFail() {
		String username = "000111222333@gmail.com";
		String password = "123456";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "t100310313@ntut.org.tw";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件\ntestAccountLoginFail";
		String ccAddresses = "";
		String result = gmailSender.send(address, ccAddresses, subject, text, null);

		assertEquals("帳號密碼不正確", result);
	}

	@Test
	public void testRecipientAddressFormatError() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "clfddzifoyfvvxqa";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "0000aaaaa@gmail,com";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件\ntestRecipientAddressFormatError";
		String ccAddresses = "";
		String result = gmailSender.send(address, ccAddresses, subject, text, null);

		assertEquals("送信位址格式不正確", result);
	}

	@Test
	public void testSendFirewallFail() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "clfddzifoyfvvxqa";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "Alice@clarence.party";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件\ntestSendFirewallFail";
		String ccAddresses = "";
		String result = gmailSender.send(address, ccAddresses, subject, text, null);

		assertNotEquals("無法連線到SMTP host，請檢察防火牆或Proxy設定", result);
	}

	@Test
	public void testSendNetwordFail() {
		String username = "news.teddysoft.tw@gmail.com";
		String password = "clfddzifoyfvvxqa";
		GmailSender gmailSender = new GmailSender(username, password);
		String address = "Alice@clarence.party";
		String subject = "泰迪軟體課程通知";
		String text = "測試信件\ntestSendNetwordFail";
		String ccAddresses = "";
		String result = gmailSender.send(address, ccAddresses, subject, text, null);

		assertNotEquals("Unknown SMTP host: smtp.gmail.com，請檢察網路連線", result);
	}
}
