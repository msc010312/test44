package CH38.Domain.Service;

import CH38.Domain.DAO.BookDAOImpl;
import CH38.Domain.DAO.BookDAOInterface;
import CH38.Domain.DTO.BookDTO;

public class BookServiceImpl {

	private BookDAOInterface bookDAO;

	private static BookServiceImpl instance;

	private BookServiceImpl() throws Exception {
		bookDAO = BookDAOImpl.getInstance();
		System.out.println("[SERVICE] BookServiceImpl init");
	};

	public static BookServiceImpl getInstance() throws Exception {
		if (instance == null)
			instance = new BookServiceImpl();
		return instance;
	}
	
	//Tx 처리 + 비지니스 유효성검사(정책)
	public boolean bookRegistration(BookDTO bookDTO) throws Exception {
		return bookDAO.insert(bookDTO)>0;
	}
}
