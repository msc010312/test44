package CH38.Domain.DAO;

import java.sql.SQLException;
import java.util.List;

import CH38.Domain.DTO.BookDTO;

public interface BookDAOInterface {

	// CRUD
	int insert(BookDTO BookDTO) throws Exception;

	int update(BookDTO BookDTO) throws SQLException;

	int delete(BookDTO BookDTO) throws SQLException;

	BookDTO select(BookDTO BookDTO);

	List<BookDTO> selectAll();

}