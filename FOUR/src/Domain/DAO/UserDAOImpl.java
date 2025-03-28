package CH38.Domain.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import CH38.Domain.DAO.ConnectionPool.ConnectionItem;
import CH38.Domain.DAO.ConnectionPool.ConnectionPool;
import CH38.Domain.DTO.UserDTO;

public class UserDAOImpl extends DAO implements UserDAOInterface {
	
	// 싱글톤 패턴처리
	private static UserDAOInterface instace;

	private UserDAOImpl() throws Exception {
		System.out.println("[DAO] UserDAOImpl init");
	}

	public static UserDAOInterface getInstance() throws Exception {
		if (instace == null)
			instace = new UserDAOImpl();
		return instace;
	}

	// CRUD
	@Override
	public int insert(UserDTO userDTO) throws Exception {
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			pstmt = conn.prepareStatement("insert into tbl_user values(?,?,?,?)");
			pstmt.setString(1, userDTO.getUserid());
			pstmt.setString(2, userDTO.getUsername());
			pstmt.setString(3, userDTO.getPassword());
			pstmt.setString(4, "ROLE_USER");
			
			connectionPool.releaseConnection(connectionItem);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("USERDAO : INSERT SQL EXCEPTION");
		} finally {
			try {pstmt.close();} catch (Exception e2) {}
		}
	}

	@Override
	public int update(UserDTO userDTO) throws SQLException {
//		try {
//			pstmt = conn.prepareStatement("");
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new SQLException("USERDAO : UPDATE SQL EXCEPTION");
//		} finally {
//			try {pstmt.close();} catch (Exception e2) {}
//		}
		return -1;
	}

	@Override
	public int delete(UserDTO userDTO) throws SQLException {
//		try {
//			pstmt = conn.prepareStatement("");
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new SQLException("USERDAO : DELETE SQL EXCEPTION");
//		} finally {
//			try {pstmt.close();} catch (Exception e2) {}
//		}
		return -1;
	}

	@Override
	public UserDTO select(UserDTO userDTO) {
		return null;
	}

	@Override
	public List<UserDTO> selectAll() {
		return null;
	}
}