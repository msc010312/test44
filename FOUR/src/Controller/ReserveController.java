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
			Integer rental_id = params.get("rental_id") != null ? (Integer) params.get("rental_id") : null;
			Integer user_id = params.get("user_id") != null ? (Integer) params.get("user_id") : null;
			String reserve_order = params.get("reserve_order") != null ? (String) params.get("reserve_order") : null;

			switch (serviceNo) {
			// 예약 추가
			case 1: {
				System.out.println("[RC] 예약 요청 확인");
				reserveDTO = new ReserveDTO(rental_id, user_id, reserve_order);
				boolean isReserved = reserveService.reserveAdd(reserveDTO);
				if (isReserved) {
					response.put("message", "예약이 완료되었습니다.");
					response.put("status", true);
				} else {
					response.put("message", "예약에 실패했습니다.");
					response.put("status", false);
				}
				break;
			}

			// 예약 조회
			case 2: {
				List<ReserveDTO> list = null;
				if (user_id != null) {
					System.out.println("[RC] 유저 아이디로 예약 조회 요청 확인");
					list = reserveService.ReserveSearchByuserId(user_id);
					response.put("data", list);
					response.put("status", true);
					return response;
				} else if (rental_id != null) {
					System.out.println("[RC] 대여 아이디로 예약 조회 요청 확인");
					list = reserveService.ReserveSearchByRentalId(rental_id);
					response.put("data", list);
					response.put("status", true);
					return response;
				} else {
					System.out.println("[RC] 전체 예약 조회 요청 확인");
					list = reserveService.ReserveSearchAll();
					response.put("status", true);
					response.put("data", list);
				}
				break;
			}

			// 예약 수정
			case 3: {
				System.out.println("[RC] 예약 수정 요청 확인");

				if (user_id != null && rental_id != null && reserve_order != null) {
					ReserveDTO updatedReserveDTO = new ReserveDTO();
					updatedReserveDTO.setUser_id(user_id);
					updatedReserveDTO.setRental_id(rental_id);
					updatedReserveDTO.setReserve_order(reserve_order);

					boolean isUpdated = reserveService.ReserveEdit(updatedReserveDTO);
					if (isUpdated) {
						response.put("message", "예약 정보가 수정되었습니다.");
						response.put("status", true);
					} else {
						response.put("message", "예약 정보 수정에 실패했습니다.");
						response.put("status", false);
					}
				} else {
					response.put("message", "수정에 필요한 정보가 부족합니다 (user_id, rental_id, reserve_order).");
					response.put("status", false);
				}
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

		return response;
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
