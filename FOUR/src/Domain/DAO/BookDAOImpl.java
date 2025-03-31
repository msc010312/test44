package Domain.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Domain.DTO.BookDTO;
import Domain.DTO.ClassificationDTO;

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
			// 1. Classification 테이블에서 필요한 값을 조회
	        String classificationId = getClassificationId(ClassificationDTO.getClassificationid());
			
			pstmt = conn.prepareStatement("INSERT INTO tbl_book (book_code, classification_id, book_auther, book_name, publisher, isreserve) values(?,?,?,?,?,?)");
			pstmt.setString(1, BookDTO.getBookCode());
			pstmt.setString(2, ClassificationDTO.getClassificationid());
			pstmt.setString(3, BookDTO.getBookAuther());
			pstmt.setString(4, BookDTO.getBookName());
			pstmt.setString(5, BookDTO.getPublisher());
			pstmt.setString(6, BookDTO.getIsreserve());
			
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BookDAO : INSERT SQL EXCEPTION");
		} finally {
			try {pstmt.close();} catch (Exception e2) {}
		}
	}

	private String getClassificationId(String classificationid) {
		// TODO Auto-generated method stub
		return null;
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