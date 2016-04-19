package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SLMDBUtility {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/SLM2016?useUnicode=true&characterEncoding=utf-8";
	static final String USER = "root";
	static final String PASS = "";
	Connection connection = null;

	public SLMDBUtility() {
		super();
	}

	public void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ResultSet selectSQL(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		if (connection == null) {
			this.createConnection();
		}
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			// rs.close();
			// stmt.close();
			// connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}

	public boolean insertSQL(String sql) {
		Statement stmt = null;
		boolean result = false;
		if (connection == null) {
			this.createConnection();
		}
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			result = true;
			// rs.close();
			// stmt.close();
			// connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateSQL(String sql) {
		Statement stmt = null;
		boolean result = false;
		if (connection == null) {
			this.createConnection();
		}
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			result = true;
			// rs.close();
			// stmt.close();
			// connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteSQL(String sql) {
		Statement stmt = null;
		boolean result = false;
		if (connection == null) {
			this.createConnection();
		}
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			result = true;
			// rs.close();
			// stmt.close();
			// connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
