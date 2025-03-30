package Domain.DAO;

import java.sql.SQLException;
import Domain.DTO.RentalDTO;

public class RentalDAO extends DAO implements RentalDAOInterface {
	public RentalDAO() throws Exception {
		super();
	}

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
			pstmt = conn.prepareStatement("insert into rental_tbl values(?,?,?)");
			pstmt.setInt(1, rentalDto.getRental_id());
			pstmt.setInt(2, rentalDto.getBook_code());
			pstmt.setInt(3, rentalDto.getUser_id());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("RentalDAO : INSERT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public int select(RentalDTO rentalDto) throws Exception {
		try {
			String sql = "SELECT * FROM rental_tbl WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rentalDto.getUser_id());
			rs = pstmt.executeQuery();
			if (rs.next()) {
	            return rs.getInt("rental_id"); // rental_id 값만 반환
	        }
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
		return -1;
	}

	@Override
	public int delete(RentalDTO rentalDto) throws Exception {
		try {
			String sql = "DELETE FROM rental_tbl WHERE rental_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rentalDto.getRental_id());

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
