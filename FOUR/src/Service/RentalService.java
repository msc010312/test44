package Service;

import java.util.List;

import Domain.DAO.RentalDAO;
import Domain.DAO.RentalDAOInterface;
import Domain.DTO.RentalDTO;

public class RentalService {
	private RentalDAOInterface rentaldao;

	private static RentalService instance;

	private RentalService() throws Exception {
		rentaldao = RentalDAO.getInstance();
		System.out.println("[SERVICE] RentalServiceImpl init");
	};

	public static RentalService getInstance() throws Exception {
		if (instance == null)
			instance = new RentalService();
		return instance;
	}

	// Tx 처리 + 비지니스 유효성검사(정책)
	public boolean insertBookRental(RentalDTO rentaldto) throws Exception {
		return rentaldao.insert(rentaldto) > 0;
	}
	public List<RentalDTO> viewRentalInfo(RentalDTO rentaldto) throws Exception {
		return rentaldao.select(rentaldto);
	}
	public boolean deleteRentalList(RentalDTO rentaldto) throws Exception {
		return rentaldao.delete(rentaldto) > 0;
	}
}
