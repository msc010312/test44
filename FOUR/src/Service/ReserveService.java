package Service;

import java.util.List;

import Domain.DAO.ReserveDAOInterface;
import Domain.DAO.ReserveDAOImpl;
import Domain.DTO.ReserveDTO;

public class ReserveService {

	private ReserveDAOInterface reserveDAO; // 인터페이스 파일

	// 싱글톤 패턴
	private static ReserveService instance;

	private ReserveService() {
		reserveDAO = ReserveDAOImpl.getInstance();
		System.out.println("[SERVICE] ReserveService init");
	}

	public static ReserveService getInstance() {
		if (instance == null) {
			instance = new ReserveService();
		}
		return instance;
	}

	// 예약 등록
	public boolean reserveAdd(ReserveDTO reserveDTO) {
		boolean isReserved = false;
		try {
			isReserved = reserveDAO.insertReserve(reserveDTO) > 0;
			System.out.println("[RS] 예약 등록 성공");
		} catch (Exception e) {
		}
		return isReserved;
	}

	// 유저 ID로 예약 조회
	public List<ReserveDTO> ReserveSearchByuserId(int user_id) {
		List<ReserveDTO> searchReserveByUser = null;
		try {
			searchReserveByUser = reserveDAO.selectReserveByUserId(user_id);
			System.out.println("[RS] 유저별 예약조회 성공");
		} catch (Exception e) {
		}
		return searchReserveByUser;
	}

	// 대여 아이디로 예약 조회
	public List<ReserveDTO> ReserveSearchByRentalId(int rental_id) {
		List<ReserveDTO> SearchReservebyRentalId = null;
		try {
			SearchReservebyRentalId = reserveDAO.selectReserveByRentalId(rental_id);
			System.out.println("[RS] 렌탈 ID별 예약 조회 성공");
		} catch (Exception e) {
		}
		return SearchReservebyRentalId;
	}

	// 전체 예약 조회
	public List<ReserveDTO> ReserveSearchAll() {
		List<ReserveDTO> SearchReserveAll = null;
		try {
			SearchReserveAll = reserveDAO.selectAllReserves();
			System.out.println("[RS] 전체 예약 조회 성공");
		} catch (Exception e) {
		}
		return SearchReserveAll;
	}

	// 예약 수정
	public boolean ReserveEdit(ReserveDTO reserveDTO) {
		boolean EditReserve = false;
		try {
			EditReserve = reserveDAO.updateReserve(reserveDTO) > 0;
			System.out.println("[RS] 예약 수정 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return EditReserve;
	}

	// 예약 삭제
	public boolean ReserveDelete(int user_id, int rental_id) {
		boolean DeleteReserve = false;
		try {
			DeleteReserve = reserveDAO.deleteReserve(user_id, rental_id) > 0;
			System.out.println("[RS] 예약 삭제 성공");
		} catch (Exception e) {
		}

		return DeleteReserve;
	}
}
