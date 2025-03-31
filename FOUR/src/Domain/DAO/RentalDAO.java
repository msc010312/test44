package Domain.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public int insert(RentalDTO rentalDto) {
		try {
			pstmt = conn.prepareStatement("insert into rental_tbl(rental_id,book_code,user_id) values(?,?,?)");
			pstmt.setInt(1, rentalDto.getRental_id());
			pstmt.setInt(2, rentalDto.getBook_code());
			pstmt.setInt(3, rentalDto.getUser_id());
			System.out.println("1");
			int rowsAffected = pstmt.executeUpdate();
	        System.out.println("rowsAffected: " + rowsAffected);  // 실행된 행 수 확인
			System.out.println("2");
			return rowsAffected;
		} catch (Exception e) {
			e.printStackTrace();
//			throw new SQLException("RentalDAO : INSERT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
		return 0;
	}

	@Override
	public List<RentalDTO> select(RentalDTO rentalDto) throws Exception {
		try {
			List<RentalDTO> list = new ArrayList();
			String sql = "SELECT * FROM rental_tbl WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rentalDto.getUser_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("user_id"));
				RentalDTO rd = new RentalDTO(rs.getInt("rental_id"), rs.getInt("book_code"), rs.getInt("user_id"));
				list.add(rd);

			}
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
