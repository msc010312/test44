package Domain.DAO;

import java.util.List;

import Domain.DTO.ReserveDTO;

public interface ReserveDAO {

	// 예약 추가
	int insertReserve(ReserveDTO reserveDTO);

	// 예약 삭제
	int deleteReserve(int reserveId);
	
	// 특정 도서에 대한 예약 목록 조회
    List<ReserveDTO> selectReserveByBookCode(int rentalId);

}
