package Domain.DAO;

import java.sql.SQLException;
import java.util.List;

import Domain.DTO.UserDTO;

public interface UserDAOInterface {

	// CRUD
	int insert(UserDTO userDTO) throws Exception;

	int update(UserDTO userDTO) throws Exception;

	int delete(UserDTO userDTO) throws Exception;

	UserDTO select(UserDTO userDTO) throws Exception;

	List<UserDTO> selectAll() throws Exception;

}