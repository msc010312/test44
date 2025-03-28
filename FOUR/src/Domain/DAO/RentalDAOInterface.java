package Domain.DAO;

import Domain.DTO.RentalDTO;

public interface RentalDAOInterface {

	// CRUD
	int insert(RentalDTO rentalDto) throws Exception;

}