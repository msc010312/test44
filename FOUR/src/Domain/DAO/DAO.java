package Domain.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class DAO {
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected static Connection conn;
	protected static final String ID = "system";
	protected static final String PW = "1234";
	protected static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";

	public DAO() throws Exception {
		connectDB();
	}

	protected void connectDB() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(URL, ID, PW);
		System.out.println("DB CONNECTED...");
	}

	public Connection getConnection() {
		return conn;
	}
}
