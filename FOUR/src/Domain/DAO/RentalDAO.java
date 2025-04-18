package Domain.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.DTO.RentalDTO;

public class RentalDAO extends DAO implements RentalDAOInterface {
	private static RentalDAO instance;

	public static RentalDAO getInstance() throws Exception {
		if (instance == null)
			instance = new RentalDAO();
		return instance;
	}

	// CRUD
	@Override
	public int insert(RentalDTO rentalDto) throws Exception {
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			pstmt = conn.prepareStatement("select book_code from rental_tbl where book_code = ? ");
			pstmt.setInt(1, rentalDto.getBook_code());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return -1; 
			}
			pstmt.close();
			pstmt = conn.prepareStatement("insert into rental_tbl(RENTAL_ID,BOOK_CODE,USER_ID) values(?,?,?)");
			pstmt.setInt(1, rentalDto.getRental_id());
			pstmt.setInt(2, rentalDto.getBook_code());
			pstmt.setInt(3, rentalDto.getUser_id());
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement("update book_tbl set isreserve = 1 where book_code = ?");
			pstmt.setInt(1, rentalDto.getBook_code());
			connectionPool.releaseConnection(connectionItem);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("RentalDAO : INSERT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<RentalDTO> select(RentalDTO rentalDto) throws Exception {
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			List<RentalDTO> list = new ArrayList<>();
			pstmt = conn.prepareStatement("SELECT * FROM rental_tbl WHERE user_id = ?");
			pstmt.setInt(1, rentalDto.getUser_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RentalDTO rd = new RentalDTO(rs.getInt("rental_id"), rs.getInt("book_code"), rs.getInt("user_id"));
				list.add(rd);
			}
			connectionPool.releaseConnection(connectionItem);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("RentalDAO : SELECT SQL EXCEPTION");
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public int delete(RentalDTO rentalDto) throws Exception {
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			pstmt = conn.prepareStatement("select book_code from rental_tbl where rental_id = ? ");
			pstmt.setInt(1, rentalDto.getRental_id());
			rs = pstmt.executeQuery();
			rs.next();
			int bc = rs.getInt("book_code");
			pstmt.close();
			pstmt = conn.prepareStatement("DELETE FROM rental_tbl WHERE rental_id = ?");
			pstmt.setInt(1, rentalDto.getRental_id());
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement("update book_tbl set isreserve = 0 where book_code = ?");
			pstmt.setInt(1, bc);
			connectionPool.releaseConnection(connectionItem);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("RentalDAO : DELETE SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
	}
}
