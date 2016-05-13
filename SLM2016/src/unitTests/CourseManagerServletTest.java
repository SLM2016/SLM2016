package unitTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import servlets.CourseManagerServlet;

public class CourseManagerServletTest {
	private CourseManagerServlet tag_;
	private String id_;

	@Before
	public void setUp() throws Exception {
		HttpServletRequest postRequestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse postResponseMock = Mockito.mock(HttpServletResponse.class);

		Reader data = new StringReader(
				"{\"courseName_\":\"Scurm敏捷方法實作班\",\"type_\":\"公開班\",\"batch_\":\"401\",\"dates_\":[\"2016-06-24\"],\"duration_\":18,\"ticketTypes_\":[\"一般票\"],\"prices_\":[33000],\"location_\":\"台北市中正區延平南路12號4樓\",\"lecturer_\":\"Teddy\",\"status_\":\"準備中\",\"ccAddresses_\":[\"test@test\"],\"hyperlink_\":\"http://teddysoft.tw\"}");
		Mockito.when(postRequestMock.getReader()).thenReturn(new BufferedReader(data));

		tag_ = new CourseManagerServlet() {
			private static final long serialVersionUID = 1L;
		};

		id_ = tag_.courseManagerWithDb_.getCourseId("Scurm敏捷方法實作班");
		Writer output = new StringWriter();
		Mockito.when(postResponseMock.getWriter()).thenReturn(new PrintWriter(output));
		tag_.doPost(postRequestMock, postResponseMock);
		assertEquals("\"Success\"", output.toString());
	}

	@After
	public void tearDown() throws Exception {
		HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);

		Mockito.when(requestMock.getHeader("Delete")).thenReturn("false");
		Reader data = new StringReader(id_);
		Mockito.when(requestMock.getReader()).thenReturn(new BufferedReader(data));

		Writer output = new StringWriter();
		Mockito.when(responseMock.getWriter()).thenReturn(new PrintWriter(output));
		tag_.doPost(requestMock, responseMock);
	}

	@Test
	public void testDoGet() throws ServletException, IOException {
		HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);

		Writer output = new StringWriter();
		Mockito.when(responseMock.getWriter()).thenReturn(new PrintWriter(output));
		tag_.doGet(requestMock, responseMock);
		assertNotEquals("[]", output.toString());
	}

	@Test
	public void testDoPostDeleteCourse() throws ServletException, IOException {
		HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);
		
		Mockito.when(requestMock.getHeader("Delete")).thenReturn("false");
		Reader data = new StringReader(id_);
		Mockito.when(requestMock.getReader()).thenReturn(new BufferedReader(data));

		Writer output = new StringWriter();
		Mockito.when(responseMock.getWriter()).thenReturn(new PrintWriter(output));
		tag_.doPost(requestMock, responseMock);
		assertEquals("Success", output.toString());
	}

	@Test
	public void testDoPostAddCourse() throws ServletException, IOException, SQLException {
		HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);

		Reader data = new StringReader(
				"{\"courseName_\":\"Scurm敏捷方法實作班\",\"type_\":\"公開班\",\"batch_\":\"401\",\"dates_\":[\"2016/6/24\"],\"duration_\":18,\"ticketTypes_\":[\"一般票\"],\"prices_\":[33000],\"location_\":\"台北市中正區延平南路12號4樓\",\"lecturer_\":\"Teddy\",\"status_\":\"準備中\",\"ccAddresses_\":[\"test@test\"],\"hyperlink_\":\"http://teddysoft.tw\"}");
		Mockito.when(requestMock.getReader()).thenReturn(new BufferedReader(data));

		String id = tag_.courseManagerWithDb_.getCourseId("Scurm敏捷方法實作班");
		
		Writer output = new StringWriter();
		Mockito.when(responseMock.getWriter()).thenReturn(new PrintWriter(output));
		tag_.doPost(requestMock, responseMock);
		assertEquals("\"Success\"", output.toString());

		HttpServletRequest deleteRequestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse deleteResponseMock = Mockito.mock(HttpServletResponse.class);

		Mockito.when(deleteRequestMock.getHeader("Delete")).thenReturn("false");
		Reader deleteData = new StringReader(id);
		Mockito.when(deleteRequestMock.getReader()).thenReturn(new BufferedReader(deleteData));

		Writer deleteOutput = new StringWriter();
		Mockito.when(deleteResponseMock.getWriter()).thenReturn(new PrintWriter(deleteOutput));
		tag_.doPost(deleteRequestMock, deleteResponseMock);
		assertEquals("Success", deleteOutput.toString());
	}
}
