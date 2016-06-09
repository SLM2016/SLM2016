package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mailSending.ClassInfomation;
import mailSending.Classes;

public class ClassesTest {
	private Classes classes_;

	@Before
	public void setUp() throws Exception {
		classes_ = new Classes();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		Classes cloneObject = classes_.clone();
		assertEquals("Scurm敏捷方法實作班", cloneObject.getClasses().get(0).getClassName());
		assertEquals("軟體重構入門實作班", cloneObject.getClasses().get(1).getClassName());
		assertEquals("Design Patterns這樣學就會了–入門實作班", cloneObject.getClasses().get(2).getClassName());
	}

	@Test
	public void testAddClass() {
		ClassInfomation classInfomation = new ClassInfomation("Design Patterns這樣學就會了–進階實作班");
		classes_.addClass(classInfomation);
		assertEquals("Design Patterns這樣學就會了–進階實作班", classes_.getClasses().get(3).getClassName());
	}
}
