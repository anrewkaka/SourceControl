package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.Constants;

public class JdbcExecute {
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://db04.serverhosting.vn/sho92404_xuany";

	// Database credentials
	private static final String USER = "sho92404_xuany";
	private static final String PASS = "Gydcuatui1";
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	private static void openconnect() throws ClassNotFoundException,
			SQLException {
		// STEP 2: Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");

		// STEP 3: Open a connection
		System.out.println("Connecting to a selected database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connected database successfully...");
		stmt = conn.createStatement();
	}

	private static void closeConnect() {
		// finally block used to close resources
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se) {
		}// do nothing
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}// end finally try
	}

	public static int executeUpdate(String sql) {
		int result = 0;
		try {
			openconnect();

			// STEP 4: Execute a query
			System.out.println("Execute query: " + sql);

			result = stmt.executeUpdate(sql);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			closeConnect();
		}// end try

		return result;
	}

	public static String executeScalar(String sql, String fieldName) {
		String result = Constants.STR_EMPTY;
		try {
			openconnect();

			// STEP 4: Execute a query
			System.out.println("Execute query: " + sql);

			rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				result = rs.getString(fieldName);
				break;
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			closeConnect();
		}// end try

		return result;
	}
}
