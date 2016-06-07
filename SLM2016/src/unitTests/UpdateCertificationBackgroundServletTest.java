package unitTests;

import static org.junit.Assert.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlets.UpdateCertificationBackgroundServlet;

public class UpdateCertificationBackgroundServletTest {
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//Case 1 : Insert a null file
	public void testdoPost_nullfile() throws IOException, ServletException {
		HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);
		ServletContext context=Mockito.mock(ServletContext.class,Mockito.RETURNS_DEEP_STUBS);
		Mockito.when(requestMock.getPart("file")).thenReturn(null);
		Mockito.when(requestMock.getParameter("courseId")).thenReturn("testBackground");
		UpdateCertificationBackgroundServlet backgroundTest=new UpdateCertificationBackgroundServlet(){
			private static final long serialVersionUID = 1L;
			public ServletContext getServletContext()
			{
				return context;
			}
		};
		Mockito.when(requestMock.getServletContext()).thenReturn(context);
		Mockito.when(requestMock.getServletContext().getRealPath(Mockito.anyString())).thenReturn("WebContent\\images\\");
		Writer output = new StringWriter();
		Mockito.when(responseMock.getWriter()).thenReturn(new PrintWriter(output));
		backgroundTest.doPost(requestMock, responseMock);
		String except = "{\"status\":\"false\"}";
		assertEquals(except, output.toString().substring(0, 18));
	}
}
