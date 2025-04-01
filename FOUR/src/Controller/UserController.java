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
				
				boolean isSuccess = userService.userJoin(userDTO);

				if (isSuccess) {
					response.put("status", isSuccess);
					response.put("message", "회원가입 성공");
				} else {
					response.put("status", isSuccess);
					response.put("message", "회원가입 실패");
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
				} else {
					response.put("status", false);
					response.put("message", "회원조회 실패");
				}
				break;
			}
			case 3: { // U - 회원 정보 수정
				System.out.println("[UC] 회원 정보 수정 요청 확인");
				userDTO = new UserDTO(user_id, user_name, user_identity, user_phone, user_addr, null);

				boolean isSuccess = userService.userEdit(userDTO);

				if (isSuccess) {
					response.put("status", isSuccess);
					response.put("message", "회원수정 성공");
				} else {
					response.put("status", isSuccess);
					response.put("message", "회원수정 실패");
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
				} else {
					response.put("status", isSuccess);
					response.put("message", "회원탈퇴 실패");
				}
				break;
			}
			default:
				System.err.println("[UC] 잘못된 요청 번호");
				response.put("status", false);
				response.put("message", "잘못된 서비스 요청입니다.");
			}
		} catch (Exception e) {
			execeptionHandler(e);
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
