package Service;

import java.sql.SQLException;

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

	// 회원가입(+TX처리필요)
	public boolean userJoin(UserDTO userDTO) throws Exception {
		boolean isJoin = false;
		try {
			isJoin  = userDAO.insert(userDTO) > 0;
			System.out.println("[US]회원가입 성공");
		} catch (SQLException e) {
		}

		return isJoin;
	};
	// 회원조회

	// 회원정보수정

	// 회원탈퇴

	// 로그인

	// 로그아웃

}
