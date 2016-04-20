package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import certification.Certification;

public class CertificationTest {
	private Certification certification_ = null;

	@Before
	public void setUp() throws Exception {
		certification_ = new Certification();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetId() {
		assertEquals(certification_.getId(), "");
		String id = certification_.getId();
		id = "id1";
		assertNotEquals(certification_.getId(), id);
	}

	@Test
	public void testGetOwner() {
		assertEquals(certification_.getOwner(), "");
		String owner = certification_.getOwner();
		owner = "owner1";
		assertNotEquals(certification_.getOwner(), owner);
	}

	@Test
	public void testSetId() {
		String id = "SCRUM1603-33";
		certification_.setId(id);
		assertEquals(certification_.getId(), id);

		id = "SCRUM1603-331";
		assertNotEquals(certification_.getId(), id);
	}

	@Test
	public void testSetOwner() {
		String owner = "Teddy";

		certification_.setOwner(owner);
		assertEquals(certification_.getOwner(), owner);

		owner = "Teddy Chen";
		assertNotEquals(certification_.getOwner(), owner);
	}

	@Test
	public void testClone() {
		String id = "SCRUM1603-33";
		String owner = "Teddy";
		certification_.setId(id);
		certification_.setOwner(owner);

		Certification cloneObject = certification_.clone();
		assertEquals(certification_.getId(), cloneObject.getId());
		assertEquals(certification_.getOwner(), cloneObject.getOwner());

		cloneObject.setId("SCRUM1603-331");
		cloneObject.setOwner("Teddy Chen");
		assertNotEquals(certification_.getId(), cloneObject.getId());
		assertNotEquals(certification_.getOwner(), cloneObject.getOwner());
	}
}
