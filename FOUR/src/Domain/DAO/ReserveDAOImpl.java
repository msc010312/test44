package Domain.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Domain.DAO.ConnectionPool.ConnectionPool;
import Domain.DTO.ReserveDTO;

public class ReserveDAOImpl extends DAO implements ReserveDAO {

	// 싱글톤 패턴처리
	private static ReserveDAOImpl instance;

	private ReserveDAOImpl() {
		System.out.println("[DAO] ReserveDAOImpl init...");
		connectionPool = ConnectionPool.getInstance();
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
			Connection conn = connectionItem.getConn();

			pstmt = conn.prepareStatement("insert into reserve_tbl values(?,?,?)");
			pstmt.setInt(1, reserveDTO.getRental_id());
			pstmt.setInt(2, reserveDTO.getMember_id());
			pstmt.setString(3, reserveDTO.getReserve_order());

			connectionPool.releaseConnection(connectionItem);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("ReserveDAO's INSERT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}

	// 도서에 대한 대여 및 예약 조회
	@Override
	public List<ReserveDTO> selectReserveByBookCode(int rental_id) {
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();

			//------------------- 여기서부터 수정
			pstmt = conn.prepareStatement("select  from reserve_tbl");
			pstmt.setInt(1, reserveDTO.getRental_id());


			connectionPool.releaseConnection(connectionItem);

			connectionPool.releaseConnection(connectionItem);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("ReserveDAO's INSERT SQL EXCEPTION");
		}
		return null;
	}

	@Override
	public List<ReserveDTO> selectReserveByMemberId(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReserveDTO> selectAllReserves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateReserve(ReserveDTO reserveDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReserve(int reserve_id) {
		// TODO Auto-generated method stub
		return 0;
	}
}