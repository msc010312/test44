package Domain.DAO;

import java.util.List;

import Domain.DTO.RentalDTO;

public interface RentalDAOInterface {

	// CRUD
	int insert(RentalDTO rentalDto) throws Exception;
	
	List<RentalDTO> select(RentalDTO rentalDto) throws Exception;
	
	int delete(RentalDTO rentalDto) throws Exception;
	

}