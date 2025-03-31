package Domain.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domain.DAO.ConnectionPool.ConnectionItem;
import Domain.DAO.ConnectionPool.ConnectionPool;

public abstract class DAO {
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected Connection conn;

	protected ConnectionPool connectionPool;
	protected ConnectionItem connectionItem;

	protected DAO() {
		connectionPool = ConnectionPool.getInstance();
	}

}
