package CH38.Domain.Service;

import java.sql.SQLException;

import CH38.Domain.DAO.UserDAOImpl;
import CH38.Domain.DAO.UserDAOInterface;
import CH38.Domain.DAO.ConnectionPool.ConnectionPool;
import CH38.Domain.DTO.UserDTO;

public class UserServiceImpl {

	private UserDAOInterface userDAO;
	private ConnectionPool connectionPool;

	private static UserServiceImpl instance;

	private UserServiceImpl() throws Exception {
		userDAO = UserDAOImpl.getInstance();
		connectionPool = ConnectionPool.getInstance();
		System.out.println("[SERVICE] UserServiceImpl init");
	}

	public static UserServiceImpl getInstance() throws Exception {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	// 회원가입(+TX처리필요)
	public boolean userJoin(UserDTO userDTO) throws Exception {
		boolean isJoin = false;
		try {
			connectionPool.beginTransaction();
			isJoin  = userDAO.insert(userDTO) > 0;
			System.out.println("[US]회원가입 성공");
			connectionPool.commitTransaction();
		} catch (SQLException e) {
			connectionPool.rollbackTransaction();
		}

		return isJoin;
	};
	// 회원조회

	// 회원정보수정

	// 회원탈퇴

	// 로그인

	// 로그아웃

}
