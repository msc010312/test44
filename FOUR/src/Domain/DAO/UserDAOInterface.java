package CH38.Domain.DAO;

import java.sql.SQLException;
import java.util.List;

import CH38.Domain.DTO.UserDTO;

public interface UserDAOInterface {

	// CRUD
	int insert(UserDTO userDTO) throws Exception;

	int update(UserDTO userDTO) throws SQLException;

	int delete(UserDTO userDTO) throws SQLException;

	UserDTO select(UserDTO userDTO);

	List<UserDTO> selectAll();

}