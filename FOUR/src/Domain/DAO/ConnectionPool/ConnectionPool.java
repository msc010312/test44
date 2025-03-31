package Domain.DAO.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	private List<ConnectionItem> connectionPool;

	// ConnectionPool 에 저장될 Connection 을 위한 변수
	private final int size = 10000; // 초기 커넥션 풀 크기
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";;
	private String id = "System";
	private String pw = "1234";

	private static ConnectionPool instance;

	private ConnectionPool() {
		connectionPool = new ArrayList(size);
		Connection conn;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loading Success");
			for (int i = 0; i < size; i++) {
				conn = DriverManager.getConnection(url, id, pw);
				conn.setAutoCommit(true);
				connectionPool.add(new ConnectionItem(conn));
				System.out.println("DB CONNECTED");
			}

		} catch (Exception e) {
			System.err.println("[ConnectionPool] Oracle JDBC 드라이버를 찾을 수 없습니다: " + e.getMessage());
		}
	}

	public static ConnectionPool getInstance() {
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	public synchronized ConnectionItem getConnection() throws SQLException {
		if (connectionPool.isEmpty()) {
			throw new SQLException("[ConnectionPool] 사용 가능한 커넥션이 없습니다.");
		}
		ConnectionItem item = connectionPool.remove(0);// 풀에서 첫 번째 커넥션 반환
		item.setUse(true); // 커넥션을 풀에서 가져올 때 사용 중으로 표시
		return item;
	}

	public synchronized void releaseConnection(ConnectionItem connectionItem) {
		if (connectionItem != null) {
			connectionItem.setUse(false); // 커넥션을 풀에 반환할 때 사용 가능으로 표시
			connectionPool.add(connectionItem);
		}
	}

}
