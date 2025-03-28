package CH38.Domain.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import CH38.Domain.DAO.ConnectionPool.ConnectionItem;
import CH38.Domain.DAO.ConnectionPool.ConnectionPool;
import CH38.Domain.DTO.BookDTO;

public class BookDAOImpl extends DAO implements BookDAOInterface {
	

	// 싱글톤 패턴처리
	private static BookDAOImpl instace;

	private BookDAOImpl() throws Exception{
		System.out.println("[DAO] BookDAOImpl init");
	}

	public static BookDAOImpl getInstance() throws Exception {
		if (instace == null)
			instace = new BookDAOImpl();
		return instace;
	}

	// CRUD
	@Override
	public int insert(BookDTO BookDTO) throws Exception {
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			pstmt = conn.prepareStatement("insert into tbl_book values(?,?,?,?)");
			pstmt.setString(1, BookDTO.getBookCode());
			pstmt.setString(2, BookDTO.getBookName());
			pstmt.setString(3, BookDTO.getPublisher());
			pstmt.setString(4, BookDTO.getIsbn());
			
			connectionPool.releaseConnection(connectionItem);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BookDAO : INSERT SQL EXCEPTION");
		} finally {
			try {pstmt.close();} catch (Exception e2) {}
		}
	}

	@Override
	public int update(BookDTO BookDTO) throws SQLException {
//		try {
//			pstmt = conn.prepareStatement("");
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new SQLException("BookDAO : UPDATE SQL EXCEPTION");
//		} finally {
//			try {pstmt.close();} catch (Exception e2) {}
//		}
		return -1;
	}

	@Override
	public int delete(BookDTO BookDTO) throws SQLException {
//		try {
//			pstmt = conn.prepareStatement("");
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new SQLException("BookDAO : DELETE SQL EXCEPTION");
//		} finally {
//			try {pstmt.close();} catch (Exception e2) {}
//		}
		return -1;
	}

	@Override
	public BookDTO select(BookDTO BookDTO) {
		return null;
	}

	@Override
	public List<BookDTO> selectAll() {
		return null;
	}
}