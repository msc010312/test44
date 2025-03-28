package CH38.Domain.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CH38.Domain.DAO.ConnectionPool.ConnectionItem;
import CH38.Domain.DAO.ConnectionPool.ConnectionPool;

public abstract class DAO {
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected ConnectionPool connectionPool;
	protected ConnectionItem connectionItem;
	protected DAO() throws SQLException{
		connectionPool = ConnectionPool.getInstance();
	}
}
