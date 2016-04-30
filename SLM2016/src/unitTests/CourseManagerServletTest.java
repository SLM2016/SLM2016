package unitTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import servlets.CourseManagerServlet;

public class CourseManagerServletTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoGet() throws ServletException, IOException {
		HttpServletRequest getRequestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse getResponseMock = Mockito.mock(HttpServletResponse.class);

		Reader data = new StringReader(
				"{\"courseName_\":\"Scurm敏捷方法實作班\",\"datch_\":\"401\",\"date_\":\"2016/6/24、25、26 (五、六、日)\",\"time_\":\"09:30~16:30 共18小時\",\"ticket_\":\"一般票\",\"price_\":33000,\"location_\":\"台北市中正區延平南路12號4樓\",\"lecturer_\":\"Teddy\",\"status_\":\"未開課\"}");
		Mockito.when(getRequestMock.getReader()).thenReturn(new BufferedReader(data));

		CourseManagerServlet tag = new CourseManagerServlet() {
			private static final long serialVersionUID = 1L;
		};

		Writer output = new StringWriter();
		Mockito.when(getResponseMock.getWriter()).thenReturn(new PrintWriter(output));
		tag.doPost(getRequestMock, getResponseMock);
		
		HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);

		output = new StringWriter();
		Mockito.when(responseMock.getWriter()).thenReturn(new PrintWriter(output));
		tag.doGet(requestMock, responseMock);
		assertEquals("[{\"courseName_\":\"Scurm敏捷方法實作班\",\"datch_\":\"401\",\"date_\":\"2016/6/24、25、26 (五、六、日)\",\"time_\":\"09:30~16:30 共18小時\",\"ticket_\":\"一般票\",\"price_\":33000,\"location_\":\"台北市中正區延平南路12號4樓\",\"lecturer_\":\"Teddy\",\"status_\":\"未開課\"}]", output.toString());
	}

	@Test
	public void testDoPost() throws ServletException, IOException {
		HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);

		Reader data = new StringReader(
				"{\"courseName_\":\"Scurm敏捷方法實作班\",\"datch_\":\"401\",\"date_\":\"2016/6/24、25、26 (五、六、日)\",\"time_\":\"09:30~16:30 共18小時\",\"ticket_\":\"一般票\",\"price_\":33000,\"location_\":\"台北市中正區延平南路12號4樓\",\"lecturer_\":\"Teddy\",\"status_\":\"未開課\"}");
		Mockito.when(requestMock.getReader()).thenReturn(new BufferedReader(data));

		CourseManagerServlet tag = new CourseManagerServlet() {
			private static final long serialVersionUID = 1L;
		};

		Writer output = new StringWriter();
		Mockito.when(responseMock.getWriter()).thenReturn(new PrintWriter(output));
		tag.doPost(requestMock, responseMock);
		assertEquals("\"success\"", output.toString());
	}
}
