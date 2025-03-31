package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.DTO.RentalDTO;
import Service.RentalService;

public class RentalController implements SubController {

	private RentalService rs;

	Map<String, Object> response;

	public RentalController() {
		try {
			rs = RentalService.getInstance();
		} catch (Exception e) {
			execeptionHandler(e);
		}
	}

	@SuppressWarnings("finally")
	@Override
	public Map<String, Object> execute(Map<String, Object> params) {
		response = new HashMap();
		Integer serviceNo = (Integer) params.get("serviceNo");
		if (serviceNo == null) {
			response.put("message", "유효하지 않은 서비스 요청입니다.");
			response.put("status", false);
			return response;
		}
		try {
			switch (serviceNo) {
			case 1: // 대여 신청
				System.out.println("대여 신청");
				int rental_id = params.get("rental_id") != null ? (int) params.get("rental_id") : null;
				int book_code = params.get("book_code") != null ? (int) params.get("book_code") : null;
				int user_id = params.get("user_id") != null ? (int) params.get("user_id") : null;
				RentalDTO inDto = new RentalDTO(rental_id, book_code, user_id);
				System.out.println(inDto);
				Boolean a = rs.insertBookRental(inDto);
				System.out.println(a);
				Boolean isOk = isValid(inDto);
				System.out.println("대여 신청 유효성 검증 확인 : " + isOk);
				if (!isOk) {
					response.put("status", false);
					return response;
				}
				
				break;
			case 2: // 대여 조회
				System.out.println("대여 조회 요청");
				int userid = params.get("user_id") != null ? (int) params.get("user_id") : 0;
				RentalDTO selDto = new RentalDTO(0, 0, userid);
				List<RentalDTO> res = rs.viewRentalInfo(selDto);
				if (res == null || res.size() == 0) {
					response.put("status", false);
					System.out.println("유저가 없습니다");
				} else {
					response.put("User", res);
				}
				return response;
			case 3: // 대여 정보 수정
				System.out.println("대여 정보 수정 요청");
				break;
			case 4: // 대여 신청 삭제
				System.out.println("대여 신청 삭제 요청");
				int rentalid = params.get("rental_id") != null ? (int) params.get("rental_id") : null;
				RentalDTO deleteDto = new RentalDTO(rentalid,0,0);
				rs.deleteRentalList(deleteDto);
				break;
			default:
				System.err.println("[BC] 잘못된 요청 번호");
				response.put("message", "잘못된 서비스 요청입니다.");
				response.put("status", false);
			}
		} catch (Exception e) {
			execeptionHandler(e);
		} finally {
			return response;
		}
	}

	private Boolean isValid(RentalDTO rentaldto) {
		if (rentaldto.getUser_id() == 0) {
			System.out.println("잘못된 아이디");
			return false;
		}
		return true;
	}

	// 예외처리함수
	public Map<String, Object> execeptionHandler(Exception e) {
		e.printStackTrace();
		if (response == null)
			response = new HashMap();
		response.put("status", false);
		response.put("message", e.getMessage());
		response.put("exeception", e);
		return response;
	}

}
