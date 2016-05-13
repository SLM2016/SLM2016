package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mailSending.SendApplySuccessfullyMail;

public class SendApplySuccessfullyMailTest {
	private SendApplySuccessfullyMail sendApplySuccessfullyMail_;

	@Before
	public void setUp() throws Exception {
		sendApplySuccessfullyMail_ = new SendApplySuccessfullyMail();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSend() {
		String studentName = "Chelly";
		String eMailAddress = "t104598007@ntut.org.tw";
		String ccAddresses = "";
		String courseName = "Scrum敏捷方法實作班";
		String courseHyperLink = "http://teddysoft.tw/courses/scrum/";
		String result = sendApplySuccessfullyMail_.Send(studentName, eMailAddress, ccAddresses, courseName,
				courseHyperLink);
		assertEquals("寄送email結束.", result);
	}
}
