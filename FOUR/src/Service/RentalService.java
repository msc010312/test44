package Service;

import Domain.DAO.RentalDAOInterface;
import Domain.DTO.RentalDTO;

public class RentalService {
	private RentalDAOInterface rentaldao;

	private static RentalService instance;

	private RentalService() throws Exception {
		rentaldao =
		System.out.println("[SERVICE] BookServiceImpl init");
	};

	public static BookServiceImpl getInstance() throws Exception {
		if (instance == null)
			instance = new RentalService();
		return instance;
	}
}
