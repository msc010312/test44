package Controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.DTO.UserDTO;
import Service.UserService;

public class UserController implements SubController {

	private UserService userService;

	private UserDTO userDTO;

	Map<String, Object> response;

	public UserController() {
		try {
			userService = UserService.getInstance();
		} catch (Exception e) {
			execeptionHandler(e);
		}
	}

	// C(1)R(2)U(3)D(4) + 로그인(5)로그아웃(6)
	@Override
	public Map<String, Object> execute(Map<String, Object> params) {
		System.out.println("[SC] UserController execute Invoke");

		response = new HashMap();
		Integer serviceNo = (Integer) params.get("serviceNo");
		if (serviceNo == null) {
			response.put("message", "유효하지 않은 서비스 요청입니다.");
			response.put("status", false);
			return response;
		}
		try {

			// 01 파라미터 받기

			Integer user_id = params.get("user_id") != null ? (Integer) params.get("user_id") : null;
			String user_name = params.get("user_name") != null ? (String) params.get("user_name") : null;
			String user_identity = params.get("user_identity") != null ? (String) params.get("user_identity") : null;
			String user_phone = params.get("user_phone") != null ? (String) params.get("user_phone") : null;
			String user_addr = params.get("user_addr") != null ? (String) params.get("user_addr") : null;

			switch (serviceNo) {
			case 1: { // C - 회원가입
				System.out.println("[UC] 회원가입 요청 확인");
				String user_grade = "ROLE_USER";
				userDTO = new UserDTO(user_id, user_name, user_identity, user_phone, user_addr, user_grade);
				Boolean isOk = isValid(userDTO);
				System.out.println("[No-1 회원가입] 유효성 검증 확인 : " + isOk);
				if (!isOk) {
					response.put("status", false);
					return response;
				}

				boolean isSuccess = userService.userJoin(userDTO);

				if (isSuccess) {
					response.put("status", isSuccess);
					response.put("message", "회원가입 성공");
				}
				break;
			}
			case 2: { // R - 회원 정보 조회
				userDTO = new UserDTO(user_id, null, null, null, null, null);
				UserDTO rUserDTO = null;
				List<UserDTO> rUserDTOL = null;
				if (userDTO.getUser_id() == null) {
					System.out.println("[UC] 회원 정보 전체 조회 요청 확인");
					rUserDTOL = userService.userSearchAll();
				} else {
					System.out.println("[UC] 회원 정보 아이디 조회 요청 확인");
					rUserDTO = userService.userSearch(userDTO);
				}

				if (rUserDTO != null || rUserDTOL != null) {
					response.put("status", true);
					response.put("message", "회원조회 성공");
					if (rUserDTO != null)
						response.put("data", rUserDTO);
					else
						response.put("data", rUserDTOL);
				}
				break;
			}
			case 3: { // U - 회원 정보 수정
				System.out.println("[UC] 회원 정보 수정 요청 확인");
				userDTO = new UserDTO(user_id, user_name, user_identity, user_phone, user_addr, null);
				Boolean isOk = isValid(userDTO);
				System.out.println("[No-1 회원수정] 유효성 검증 확인 : " + isOk);
				if (!isOk) {
					response.put("status", false);
					return response;
				}

				boolean isSuccess = userService.userEdit(userDTO);

				if (isSuccess) {
					response.put("status", isSuccess);
					response.put("message", "회원수정 성공");
				}
				break;
			}
			case 4: { // D - 회원 탈퇴
				System.out.println("[UC] 회원 탈퇴 요청 확인");
				userDTO = new UserDTO(user_id, null, null, null, null, null);
				
				boolean isSuccess = userService.userLeave(userDTO);

				if (isSuccess) {
					response.put("status", isSuccess);
					response.put("message", "회원탈퇴 성공");
				}
				break;
			}
			case 5: { // P - 로그인
				System.out.println("[UC] 로그인 요청 확인");
				break;
			}
			case 6: { // P - 로그아웃
				System.out.println("[UC] 로그아웃 요청 확인");
				break;
			}
			default:
				System.err.println("[UC] 잘못된 요청 번호");
				response.put("message", "잘못된 서비스 요청입니다.");
				response.put("status", false);
			}
		} catch (Exception e) {
			execeptionHandler(e);
		}
		return response;
	}

	private boolean isValid(UserDTO userDTO) {
//		if (userDTO.getUser_id() == null || userDTO.getUser_id().length() <= 4) {
//			response.put("error", "[INVALID] userid의 길이는 최소 5자 이상이어야 합니다");
//			System.out.println("[INVALID] userid의 길이는 최소 5자 이상이어야 합니다");
//			return false;
//		}
//		if (userDTO.getUser_id().matches("^[0-9].*")) {
//			response.put("error", "[INVALID] userid의 첫문자로 숫자가 들어올 수 없습니다.");
//			System.out.println("[INVALID] userid의 첫문자로 숫자가 들어올 수 없습니다.");
//			return false;
//		}
		// NULL 체크 / 데이터(자료)수준에서의 의미있는 데이터가 포함되어져 있는지 여부
		// userid 는 첫문자가 숫자인지 여부 or 길이 체크
		// username 은 첫문자가 숫자인지 여부
		// password 복잡도체크는 bnusiness(Policy에 의한 처리)
		return true;
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
