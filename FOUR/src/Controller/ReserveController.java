package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.DTO.ReserveDTO;
import Service.ReserveService;

public class ReserveController implements SubController {

	private ReserveService reserveService;

	private ReserveDTO reserveDTO;

	Map<String, Object> response;

	public ReserveController() {
		try {
			reserveService = ReserveService.getInstance();
		} catch (Exception e) {
			execeptionHandler(e);
		}
	}

	// CRUD
	@Override
	public Map<String, Object> execute(Map<String, Object> params) {
		System.out.println("[RC] Reserve Controller execute Invoke");

		response = new HashMap();
		Integer serviceNo = (Integer) params.get("serviceNo");
		if (serviceNo == null) {
			response.put("message", "유효하지 않은 서비스 요청입니다.");
			response.put("status", false);
			return response;
		}
		try {

			// 파라미터 받기
			// params 에서 rental_id, user_id, reserve_order 추출
			int rental_id = params.get("rental_id") != null ? (int) params.get("rental_id") : 0;
			int user_id = params.get("user_id") != null ? (int) params.get("user_id") : 0;
			String reserve_order = params.get("reserve_order") != null ? (String) params.get("reserve_order") : null;

			switch (serviceNo) {
			// 예약 추가
			case 1: {
				System.out.println("[RC] 예약 요청 확인");
				reserveDTO = new ReserveDTO(rental_id, user_id, reserve_order);
				Boolean result = reserveService.reserveAdd(reserveDTO);
				if (result) {
					response.put("message", "삽입 성공");
				} else {
					response.put("message", "삽입 실패");
				}
				return response;
			}

			// 대여 아이디로 예약 조회
			case 2: {
				System.out.println("[RC] 대여 아이디로 예약 조회 요청 확인");
				if (user_id != 0) {
					List<ReserveDTO> list = reserveService.ReserveSearchByuserId(user_id);
					response.put("data", list);
					return response;
				}

				break;
			}

			// 수정
			case 3: {
				break;
			}

			// 삭제
			case 4: {
				break;
			}
			default:
				System.err.println("[UC] 잘못된 요청 번호");
				response.put("message", "잘못된 서비스 요청입니다.");
				response.put("status", false);
			}

		} catch (Exception e) {
		}

		return null;
	}

	// 예외처리함수
	public Map<String, Object> execeptionHandler(Exception e) {
		if (response == null)
			response = new HashMap();
		response.put("status", false);
		response.put("message", e.getMessage());
		response.put("exeception", e);
		return response;
	}

}
