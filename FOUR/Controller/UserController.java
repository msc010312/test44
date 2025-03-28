package CH38.Controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import CH38.Domain.DTO.UserDTO;
import CH38.Domain.Service.UserServiceImpl;

public class UserController implements SubController {

	private UserServiceImpl userService;

	Map<String, Object> response;

	public UserController() {
		try {
			userService = UserServiceImpl.getInstance();
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
			switch (serviceNo) {
			// 01 파라미터 받기
			case 1: { // C - 회원가입
				System.out.println("[UC] 회원가입 요청 확인");
				String userid = params.get("userid") != null ? (String) params.get("userid") : null;
				String username = params.get("username") != null ? (String) params.get("username") : null;
				String password = params.get("password") != null ? (String) params.get("password") : null;
				String role = "ROLE_USER";
				UserDTO userDTO = new UserDTO(userid, username, password, role);
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
				System.out.println("[UC] 회원 정보 조회 요청 확인");
				break;
			}
			case 3: { // U - 회원 정보 수정
				System.out.println("[UC] 회원 정보 수정 요청 확인");
				break;
			}
			case 4: { // D - 회원 탈퇴
				System.out.println("[UC] 회원 탈퇴 요청 확인");
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
		if (userDTO.getUserid() == null || userDTO.getUserid().length() <= 4) {
			response.put("error", "[INVALID] userid의 길이는 최소 5자 이상이어야 합니다");
			System.out.println("[INVALID] userid의 길이는 최소 5자 이상이어야 합니다");
			return false;
		}
		if (userDTO.getUserid().matches("^[0-9].*")) {
			response.put("error", "[INVALID] userid의 첫문자로 숫자가 들어올 수 없습니다.");
			System.out.println("[INVALID] userid의 첫문자로 숫자가 들어올 수 없습니다.");
			return false;
		}
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
