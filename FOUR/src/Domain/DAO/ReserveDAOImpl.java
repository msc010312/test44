package Domain.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.DTO.ReserveDTO;

public class ReserveDAOImpl extends DAO implements ReserveDAO {

	// 싱글톤 패턴처리
	private static ReserveDAOImpl instance;

	private ReserveDAOImpl() {
		System.out.println("[DAO] ReserveDAOImpl init...");
	}

	public static ReserveDAOImpl getInstance() {
		if (instance == null) {
			instance = new ReserveDAOImpl();
		}
		return instance;
	}

	// CRUD
	// 예약 추가
	@Override
	public int insertReserve(ReserveDTO reserveDTO) throws Exception {		
		try {
			connectionItem = connectionPool.getConnection();
			conn = connectionItem.getConn();
			System.out.println(reserveDTO.getRental_id());
			System.out.println(reserveDTO.getUser_id());
			System.out.println(reserveDTO.getReserve_order());
			pstmt = conn
					.prepareStatement("INSERT INTO reserve_tbl(rental_id, user_id, reserve_order) VALUES(?, ?, ?)");
			pstmt.setInt(1, reserveDTO.getRental_id());
			pstmt.setInt(2, reserveDTO.getUser_id());
			pstmt.setString(3, reserveDTO.getReserve_order());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("ReserveDAO's INSERT SQL EXCEPTION");
		} finally {
			try {
				connectionPool.releaseConnection(connectionItem); // 자원제거
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}

	// 대여 아이디로 도서 예약 조회
	@Override
	public List<ReserveDTO> selectReserveByRentalId(int rental_id) {
		List<ReserveDTO> reserveList = new ArrayList();
		connectionItem = null;
		pstmt = null;
		rs = null;
		try {
			connectionItem = connectionPool.getConnection();
			conn = connectionItem.getConn();

			pstmt = conn.prepareStatement("SELECT * FROM reserve_tbl WHERE rental_id = ?");
			pstmt.setInt(1, rental_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReserveDTO reserveDTO = new ReserveDTO();
				reserveDTO.setRental_id(rs.getInt("rental_id"));
				reserveDTO.setUser_id(rs.getInt("user_id"));
				reserveDTO.setReserve_order(rs.getString("reserve_order"));
				reserveList.add(reserveDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ReserveDAO's SELECT BY RENTALID SQL EXCEPTION");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connectionItem != null)
					connectionPool.releaseConnection(connectionItem); // 자원제거
			} catch (Exception e2) {
			}
		}
		return reserveList;
	}

	// 사용자에 대한 대여 및 예약조회
	@Override
	public List<ReserveDTO> selectReserveByUserId(int user_id) {
		List<ReserveDTO> reserveList = new ArrayList();
		connectionItem = null;
		pstmt = null;
		rs = null;
		try {
			connectionItem = connectionPool.getConnection();
			conn = connectionItem.getConn();

			pstmt = conn.prepareStatement("SELECT * FROM reserve_tbl WHERE user_id = ?");
			pstmt.setInt(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReserveDTO reserveDTO = new ReserveDTO();
				reserveDTO.setUser_id(rs.getInt("user_id"));
				reserveDTO.setRental_id(rs.getInt("rental_id"));
				reserveDTO.setReserve_order(rs.getString("reserve_order"));
				reserveList.add(reserveDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ReserveDAO's SELECT BY USER SQL EXCEPTION");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connectionItem != null)
					connectionPool.releaseConnection(connectionItem); // 자원제거
			} catch (Exception e2) {
			}
		}
		return reserveList;
	}

	// 모든 대여 및 예약 조회
	@Override
	public List<ReserveDTO> selectAllReserves() {
		List<ReserveDTO> reserveList = new ArrayList();
		connectionItem = null;
		pstmt = null;
		rs = null;
		try {
			connectionItem = connectionPool.getConnection();
			conn = connectionItem.getConn();

			pstmt = conn.prepareStatement("SELECT * FROM reserve_tbl");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReserveDTO reserveDTO = new ReserveDTO();
				reserveDTO.setUser_id(rs.getInt("user_id"));
				reserveDTO.setRental_id(rs.getInt("rental_id"));
				reserveDTO.setReserve_order(rs.getString("reserve_order"));
				reserveList.add(reserveDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ReserveDAO's SELECT ALL RESERVES SQL EXCEPTION");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connectionItem != null)
					connectionPool.releaseConnection(connectionItem); // 자원제거
			} catch (Exception e2) {
			}
		}
		return reserveList;
	}

	// 예약 수정(U)
	@Override
	public int updateReserve(ReserveDTO reserveDTO) throws SQLException {
		connectionItem = null;
		pstmt = null;
		try {
			connectionItem = connectionPool.getConnection();
			conn = connectionItem.getConn();

			pstmt = conn.prepareStatement("UPDATE reserve_tbl set reserve_order=? WHERE rental_id=? AND user_id=?;");
			pstmt.setString(1, reserveDTO.getReserve_order());
			pstmt.setInt(2, reserveDTO.getRental_id());
			pstmt.setInt(3, reserveDTO.getUser_id());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("ReserveDAO's UPDATE SQL EXCEPTION");
		} finally {
			try {
				connectionPool.releaseConnection(connectionItem); // 자원제거
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}

	// 예약 삭제
	@Override
	public int deleteReserve(int user_id, int rental_id) throws SQLException {
		int result = 0;
		try {
			connectionItem = connectionPool.getConnection();
			conn = connectionItem.getConn();

			pstmt = conn.prepareStatement("DELETE FROM reserve_tbl WHERE user_id=? AND rental_id=?");
			pstmt.setInt(1, user_id);
			pstmt.setInt(2, rental_id);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("ReserveDAO's DELETE SQL EXCEPTION");
		} finally {
			try {
				connectionPool.releaseConnection(connectionItem); // 자원제거
				pstmt.close();
			} catch (Exception e2) {
			}
		}
		return result;
	}
}