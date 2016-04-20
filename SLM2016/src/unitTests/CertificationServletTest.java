package unitTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import servlets.CertificationServlet;

public class CertificationServletTest{
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoPost() throws ServletException, IOException {
		HttpServletRequest requestMock=Mockito.mock(HttpServletRequest.class);
		HttpServletResponse responseMock=Mockito.mock(HttpServletResponse.class);
		
		Reader data=new StringReader("{\"id_\":\"SCRUM1603-33\",\"owner_\":\"陳泰迪\"}");
		Mockito.when(requestMock.getReader()).thenReturn(new BufferedReader(data));
		
		ServletContext context=Mockito.mock(ServletContext.class,Mockito.RETURNS_DEEP_STUBS);
		CertificationServlet tag=new CertificationServlet(){
			private static final long serialVersionUID = 1L;

			public ServletContext getServletContext()
			{
				return context;
			}
		};
		
		Mockito.when(requestMock.getServletContext()).thenReturn(context);
		Mockito.when(requestMock.getServletContext().getRealPath(Mockito.anyString())).thenReturn("WebContent\\images\\download.png");
		
		Writer output=new StringWriter();
		Mockito.when(responseMock.getWriter()).thenReturn(new PrintWriter(output));
		tag.doPost(requestMock, responseMock);
		assertEquals(output.toString(), "iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHUlEQVR42mP4//8/w38Ghv9wDCaRBaCYFEEsZgIADvU1y6BjSRkAAAAASUVORK5CYII=");
	}
}
