package Service;

import java.sql.SQLException;
import java.util.List;

import Domain.DAO.UserDAO;
import Domain.DAO.UserDAOInterface;
import Domain.DTO.UserDTO;

public class UserService {

	private UserDAOInterface userDAO;

	private static UserService instance;

	private UserService() throws Exception {
		userDAO = UserDAO.getInstance();
		System.out.println("[SERVICE] UserServiceImpl init");
	}

	public static UserService getInstance() throws Exception {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	public boolean userJoin(UserDTO userDTO) throws Exception {
		boolean isJoin = false;
		try {
			isJoin = userDAO.insert(userDTO) > 0;
			System.out.println("[US]회원가입 성공");
		} catch (SQLException e) {
		}

		return isJoin;
	};

	// 회원조회
	public UserDTO userSearch(UserDTO userDTO) throws Exception {
		UserDTO rUserDTO = null;
		try {
			rUserDTO = userDAO.select(userDTO);
			System.out.println("[US]회원조회 성공");
		} catch (SQLException e) {
		}
		return rUserDTO;
	};
	
	public List<UserDTO> userSearchAll() throws Exception {
		List<UserDTO> rUserDTOL = null;
		try {
			rUserDTOL = userDAO.selectAll();
			System.out.println("[US]회원 전체조회 성공");
		} catch (SQLException e) {
		}
		return rUserDTOL;
	};

	// 회원정보수정
	public boolean userEdit(UserDTO userDTO) throws Exception {
		boolean isJoin = false;
		try {
			isJoin = userDAO.update(userDTO) > 0;
			System.out.println("[US]회원수정 성공");
		} catch (SQLException e) {
		}

		return isJoin;
	};

	// 회원탈퇴
	public boolean userLeave(UserDTO userDTO) throws Exception {
		boolean isJoin = false;
		try {
			isJoin = userDAO.delete(userDTO) > 0;
			System.out.println("[US]회원탈퇴 성공");
		} catch (SQLException e) {
		}

		return isJoin;
	};

	// 로그인

	// 로그아웃

}
