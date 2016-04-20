package unitTests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import certification.TemplateCertification;

public class TemplateCertificationTest {
	private TemplateCertification template_ = null;

	@Before
	public void setUp() throws Exception {
		template_=new TemplateCertification();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetIdLocation() {
		assertEquals(template_.getIdLocation(), new Point(0, 0));
		Point location=template_.getIdLocation();
		location.x=1;
		assertEquals(template_.getIdLocation(), new Point(0, 0));
	}

	@Test
	public void testGetOwnerLocation() {
		assertEquals(template_.getOwnerLocation(), new Point(0, 0));
		Point location=template_.getOwnerLocation();
		location.y=-1;
		assertEquals(template_.getOwnerLocation(), new Point(0, 0));
	}

	@Test
	public void testSetIdLocation() {
		Point idLocation = new Point(123, 456);
		template_.setIdLocation(idLocation);
		assertEquals(template_.getIdLocation(), idLocation);

		idLocation.x = 100;
		idLocation.y = -12;
		assertNotEquals(template_.getIdLocation(), idLocation);
	}

	@Test
	public void testSetOwnerLocation() {
		Point ownerLocation = new Point(80, 53);
		template_.setOwnerLocation(ownerLocation);
		assertEquals(template_.getOwnerLocation(), ownerLocation);

		ownerLocation.x = 53;
		ownerLocation.y = 80;
		assertNotEquals(template_.getOwnerLocation(), ownerLocation);
	}
	
	@Test
	public void testSetIdTextSize()
	{
		template_.setIdTextSize(10);
		assertEquals(template_.getIdTextSize(),10);
	}
	
	@Test
	public void testSetOwnerTextSize()
	{
		template_.setOwnerTextSize(3);
		assertEquals(template_.getOwnerTextSize(),3);
	}
	
	@Test
	public void testClone() {
		int idTextSize=63;
		int ownerTextSize=158;
		Point idLocation = new Point(123, 456);
		Point ownerLocation = new Point(80, 53);
		template_.setIdLocation(idLocation);
		template_.setOwnerLocation(ownerLocation);
		template_.setIdTextSize(idTextSize);
		template_.setOwnerTextSize(ownerTextSize);

		TemplateCertification cloneObject = template_.clone();
		assertEquals(template_.getIdLocation(), cloneObject.getIdLocation());
		assertEquals(template_.getOwnerLocation(), cloneObject.getOwnerLocation());
		assertEquals(template_.getIdTextSize(), cloneObject.getIdTextSize());
		assertEquals(template_.getOwnerTextSize(), cloneObject.getOwnerTextSize());

		cloneObject.setIdLocation(new Point(456, 123));
		cloneObject.setOwnerLocation(new Point(53, 80));
		cloneObject.setIdTextSize(3);
		cloneObject.setOwnerTextSize(7);
		assertNotEquals(template_.getIdLocation(), cloneObject.getIdLocation());
		assertNotEquals(template_.getOwnerLocation(), cloneObject.getOwnerLocation());
		assertNotEquals(template_.getIdTextSize(), cloneObject.getIdTextSize());
		assertNotEquals(template_.getOwnerTextSize(), cloneObject.getOwnerTextSize());
	}
}
