package unitTests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.rowset.CachedRowSetImpl;

import util.SqlHelper;

public class SqlHelperTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SqlHelper helper = new SqlHelper();
		CachedRowSet data = new CachedRowSetImpl();
		assertEquals("Success", helper.excuteSql("CREATE TABLE `for_test` (`id` INT NOT NULL,	`data` VARCHAR(50) NOT NULL)", data));
		assertEquals("Success", helper.excuteSql("INSERT INTO `for_test` (`id`, `data`) VALUES (456, 'GE');", data));
		assertEquals("Success", helper.excuteSql("INSERT INTO `for_test` (`id`, `data`) VALUES (132, 'dd');", data));	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SqlHelper helper = new SqlHelper();
		CachedRowSet data = new CachedRowSetImpl();
		assertEquals("Success", helper.excuteSql("DROP TABLE `for_test`", data));
	}

	@Before
	public void setUp() throws Exception {	
	}

	@After
	public void tearDown() throws Exception {
	}
		
	@Test
	public void testInsertSqlCommand() throws SQLException {
		SqlHelper helper = new SqlHelper();
		CachedRowSet data = new CachedRowSetImpl();
		assertEquals("Success", helper.excuteSql("INSERT INTO `for_test` (`id`, `data`) VALUES (456, 'AA');", data));
	}
	
	@Test
	public void testDeleteSqlCommand() throws SQLException{
		SqlHelper helper = new SqlHelper();
		CachedRowSet data = new CachedRowSetImpl();
		assertEquals("Success", helper.excuteSql("DELETE FROM `for_test` WHERE `id`=456 AND `data`='AA';", data));
	}

	@Test
	public void testSelectSqlCommand() throws SQLException {
		SqlHelper helper = new SqlHelper();
		CachedRowSet data = new CachedRowSetImpl();
		assertEquals("Success", helper.excuteSql("SELECT `data` from `for_test` where id=456", data));
		data.next();
		assertEquals("GE", data.getString("data"));
	}
	
	@Test
	public void testUpdateSqlCommand() throws SQLException
	{
		SqlHelper helper = new SqlHelper();
		CachedRowSet data = new CachedRowSetImpl();
		assertEquals("Success", helper.excuteSql("UPDATE `for_test` SET `data`='GA' WHERE `id`=132 AND `data`='GE';", data));
		assertEquals("Success", helper.excuteSql("UPDATE `for_test` SET `data`='GE' WHERE `id`=132 AND `data`='GA';", data));
	}
}
