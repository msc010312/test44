package Domain.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.DAO.ConnectionPool.ConnectionItem;
import Domain.DAO.ConnectionPool.ConnectionPool;
import Domain.DTO.BookDTO;
import Domain.DTO.UserDTO;

public class BookDAOImpl extends DAO implements BookDAOInterface {

	private PreparedStatement pstmt;
	private ResultSet rs;

	private ConnectionPool connectionPool;
	private ConnectionItem connectionItem;

	// 싱글톤 패턴처리
	private static BookDAOImpl instace;

	private BookDAOImpl() throws Exception {
		System.out.println("[DAO] BookDAOImpl init");
		connectionPool = ConnectionPool.getInstance();
		System.out.println("BookDaoImpl DB Connection Success");
	}

	public static BookDAOImpl getInstance() throws Exception {
		if (instace == null)
			instace = new BookDAOImpl();
		return instace;
	}

	// CRUD
	@Override
	public int insert(BookDTO BookDTO) throws Exception {
		PreparedStatement pstmt = null;
		try {
			// 1. Classification 테이블에서 필요한 값을 조회
//	        int classificationId = getClassificationId(BookDTO.getClassification_id());

			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();

			pstmt = conn.prepareStatement("INSERT INTO book_tbl values(?,?,?,?,?,?)");
			pstmt.setInt(1, BookDTO.getBook_Code());
			pstmt.setInt(2, BookDTO.getClassification_id());
			pstmt.setString(3, BookDTO.getBook_Auther());
			pstmt.setString(4, BookDTO.getBook_Name());
			pstmt.setString(5, BookDTO.getPublisher());
			pstmt.setInt(6, BookDTO.getIsreserve());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BookDAO : INSERT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}

	// Classification 테이블에서 classificationId를 조회하는 메소드
	private int getClassificationId(int classificationId) throws SQLException {
		int resultId = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {			
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			// SQL 쿼리 실행
			String sql = "SELECT classification_id FROM classification_tbl WHERE classification_Id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classificationId);
			rs = pstmt.executeQuery();

			// 결과 처리
			if (rs.next()) {
				classificationId = rs.getInt("classification_id");
			} else {
				throw new SQLException("No classification found with Id: " + classificationId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error fetching classification ID");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
		}

		return classificationId;
	}

	@Override
	public int update(BookDTO BookDTO) throws SQLException {

		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();

			String sql = "UPDATE book_tbl SET book_Author = ?, book_Name = ?, publisher = ?, isreserve = ? WHERE book_Code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BookDTO.getBook_Auther());
			pstmt.setString(2, BookDTO.getBook_Name());
			pstmt.setString(3, BookDTO.getPublisher());
			pstmt.setInt(4, BookDTO.getIsreserve());
			pstmt.setInt(5, BookDTO.getBook_Code());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BookDAO : UPDATE SQL EXCEPTION");
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();					
					connectionPool.releaseConnection(connectionItem);
				} catch (SQLException e2) {
				}
		}

	}

	@Override
	public int delete(BookDTO BookDTO) throws SQLException {
		try {

			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			pstmt = conn.prepareStatement("delete from book_tbl where book_Code = ?");
			pstmt.setInt(1, BookDTO.getBook_Code());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO : INSERT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
				conn.close();
				connectionPool.releaseConnection(connectionItem);
			} catch (Exception e2) {
			}
		}

	}

	@Override
	public BookDTO select(BookDTO BookDTO) throws SQLException {

		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			pstmt = conn.prepareStatement("select * from book_tbl where book_name = ?");
			pstmt.setString(1, BookDTO.getBook_Name());

			rs = pstmt.executeQuery();
			rs.next();
			BookDTO bookDTO2 = new BookDTO(rs.getInt("book_code"), rs.getInt("classification_id"),
					rs.getString("book_Author"), rs.getString("book_Name"), rs.getString("publisher"),
					rs.getInt("isreserve"));
			return bookDTO2;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO : SELECT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
				conn.close();
				connectionPool.releaseConnection(connectionItem);
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public List<BookDTO> selectAll() throws Exception {
		try {
			List<BookDTO> bookList = new ArrayList();
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();

			String sql = "SELECT * FROM book_tbl";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookDTO book = new BookDTO();
				book.setBook_Code(rs.getInt("book_Code"));
				book.setClassification_id(rs.getInt("classification_id"));
				book.setBook_Auther(rs.getString("book_Author"));
				book.setBook_Name(rs.getString("book_Name"));
				book.setPublisher(rs.getString("publisher"));
				book.setIsreserve(rs.getInt("isreserve"));
				bookList.add(book);
			}
			return bookList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BookDAO : SELECTALL SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
				conn.close();
				connectionPool.releaseConnection(connectionItem);
			} catch (Exception e2) {

			}
		}
	}
}