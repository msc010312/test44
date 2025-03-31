package Domain.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Domain.DAO.ConnectionPool.ConnectionItem;
import Domain.DAO.ConnectionPool.ConnectionPool;
import Domain.DTO.BookDTO;

public class BookDAOImpl extends DAO implements BookDAOInterface {
	
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private ConnectionPool connectionPool;
	private ConnectionItem connectionItem;
	
	// 싱글톤 패턴처리
	private static BookDAOImpl instace;

	private BookDAOImpl() throws Exception{
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
//	        String classificationId = getClassificationId(BookDTO.getClassification_id());
		
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
			try {pstmt.close();} catch (Exception e2) {}
		}
	}
	
    // Classification 테이블에서 classificationId를 조회하는 메소드
    private String getClassificationId(String classificationName) throws SQLException {
        String classificationId = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // SQL 쿼리 실행
            String sql = "SELECT classification_id FROM classification_tbl WHERE classification_name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, classificationName);  // getClassificationName()으로 수정
            rs = pstmt.executeQuery();

            // 결과 처리
            if (rs.next()) {
                classificationId = rs.getString("classification_id");
            } else {
                throw new SQLException("No classification found with name: " + classificationName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching classification ID");
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
        }

        return classificationId;
    }

	@Override
	public int update(BookDTO BookDTO) throws SQLException {
		try {
			pstmt = conn.prepareStatement("");
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BookDAO : UPDATE SQL EXCEPTION");
		} finally {
			try {pstmt.close();} catch (Exception e2) {}
		}
		
	}

	@Override
	public int delete(BookDTO BookDTO) throws SQLException {
		try {
			pstmt = conn.prepareStatement("");
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BookDAO : DELETE SQL EXCEPTION");
		} finally {
			try {pstmt.close();} catch (Exception e2) {}
		}
		
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