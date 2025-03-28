package Service;

import Domain.DAO.RentalDAO;
import Domain.DAO.RentalDAOInterface;

public class RentalService {
	private RentalDAOInterface rentaldao;

	private static RentalService instance;

	private RentalService() throws Exception {
		rentaldao = RentalDAO.getInstance();
		System.out.println("[SERVICE] BookServiceImpl init");
	};

	public static RentalService getInstance() throws Exception {
		if (instance == null)
			instance = new RentalService();
		return instance;
	}
}
