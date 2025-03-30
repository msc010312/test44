package Domain.DAO;

import Domain.DTO.RentalDTO;

public interface RentalDAOInterface {

	// CRUD
	int insert(RentalDTO rentalDto) throws Exception;
	
	int select(RentalDTO rentalDto) throws Exception;
	
	int delete(RentalDTO rentalDto) throws Exception;
	

}