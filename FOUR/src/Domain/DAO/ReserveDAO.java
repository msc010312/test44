package Domain.DAO;

import java.sql.SQLException;
import java.util.List;

import Domain.DTO.ReserveDTO;

public interface ReserveDAO {

	// 예약 추가(C)
	int insertReserve(ReserveDTO reserveDTO) throws Exception;
	
	// 예약 읽기(R)
	// 도서에 대한 대여 및 예약 조회
	List<ReserveDTO> selectReserveByBookCode(int rental_id);
	
	// 사용자에 대한 대여 및 예약조회
	List<ReserveDTO> selectReserveByUserId(int user_id);

	// 모든 대여 및 예약 조회
	List<ReserveDTO> selectAllReserves();
	
	// 예약 수정(U)
	int updateReserve(ReserveDTO reserveDTO) throws SQLException;
	
	// 예약 삭제(D)
	int deleteReserve(int reserve_id);
}
