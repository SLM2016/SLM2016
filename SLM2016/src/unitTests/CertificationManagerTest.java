package unitTests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import certification.CertificationManager;

public class CertificationManagerTest {
	CertificationManager manager_=null;
	
	@Before
	public void setUp() throws Exception {
		manager_=new CertificationManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFileExtendsion() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		CertificationManager manager=CertificationManager.class.newInstance();
		Method method=manager.getClass().getDeclaredMethod("getFileExtendsion", String.class);
		method.setAccessible(true);
		
		assertEquals((String) method.invoke(manager, "asdf.txt"), "txt");
		assertEquals((String) method.invoke(manager, "C:\\Users\\User\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SLM2016\\Images\\download.png"), "png");	
		
	}

}
