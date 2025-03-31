package Domain.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.DTO.UserDTO;

public class UserDAO extends DAO implements UserDAOInterface {

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "system";
	String pw = "1234";

	// 싱글톤 패턴처리
	private static UserDAOInterface instance;

	private UserDAO() throws Exception {
		System.out.println("[DAO] UserDAOImpl init");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("[DAO] UserDAOImpl DB Success");
	}

	public static UserDAOInterface getInstance() throws Exception {
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}

	// CRUD
	@Override
	public int insert(UserDTO userDTO) throws Exception {
		try {

			pstmt = conn.prepareStatement("insert into user_tbl values(?,?,?,?,?,?)");
			pstmt.setInt(1, userDTO.getUser_id());
			pstmt.setString(2, userDTO.getUser_name());
			pstmt.setString(3, userDTO.getUser_identity());
			pstmt.setString(4, userDTO.getUser_phone());
			pstmt.setString(5, userDTO.getUser_addr());
			pstmt.setString(6, "ROLE_USER");

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("USERDAO : INSERT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public int update(UserDTO userDTO) throws SQLException {
		try {

			pstmt = conn.prepareStatement(
					"update user_tbl set user_name = ?, user_identity = ?, user_phone = ?, user_addr = ? where user_id = ?");
			pstmt.setString(1, userDTO.getUser_name());
			pstmt.setString(2, userDTO.getUser_identity());
			pstmt.setString(3, userDTO.getUser_phone());
			pstmt.setString(4, userDTO.getUser_addr());
			pstmt.setInt(5, userDTO.getUser_id());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("USERDAO : UPDATE SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public int delete(UserDTO userDTO) throws Exception {
		try {

			pstmt = conn.prepareStatement("delete from user_tbl where user_id = ?");
			pstmt.setInt(1, userDTO.getUser_id());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("USERDAO : INSERT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public UserDTO select(UserDTO userDTO) throws Exception {
		try {

			pstmt = conn.prepareStatement("select * from user_tbl where user_id = ?");
			pstmt.setInt(1, userDTO.getUser_id());

			rs = pstmt.executeQuery();
			rs.next();
			UserDTO rUserDTO = new UserDTO(rs.getInt("user_id"), rs.getString("user_name"),
					rs.getString("user_identity"), rs.getString("user_phone"), rs.getString("user_addr"),
					rs.getString("user_grade"));

			return rUserDTO;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("USERDAO : SELECT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public List<UserDTO> selectAll() throws Exception {
		try {
			List<UserDTO> rUserDTOL = new ArrayList();
			pstmt = conn.prepareStatement("select * from user_tbl");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("user_id"));

				rUserDTOL
						.add(new UserDTO(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_identity"),
								rs.getString("user_phone"), rs.getString("user_addr"), rs.getString("user_grade")));
			}
			return rUserDTOL;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("USERDAO : SELECTALL SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}
}