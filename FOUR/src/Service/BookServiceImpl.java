package Service;

import java.sql.SQLException;
import java.util.List;

import Domain.DAO.BookDAOImpl;
import Domain.DAO.BookDAOInterface;
import Domain.DTO.BookDTO;
import Domain.DTO.UserDTO;

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
		boolean bookJoin = false;
		try {
			bookJoin = bookDAO.insert(bookDTO) > 0;
			System.out.println("[BS]도서추가 성공");
		} catch (SQLException e) {
		}

		return bookJoin;
	};
	
	// 도서조회
		public BookDTO bookSearch(BookDTO bookDTO) throws Exception {
			BookDTO book1DTO = null;
			try {
			book1DTO = bookDAO.select(bookDTO);
			System.out.println("[BS]도서조회 성공");
			} catch(SQLException e) {
				
			}
			return book1DTO;
		};
		
		public List<BookDTO> bookSearchAll() throws Exception {
			List<BookDTO> book1DTOL = null;
			try {
			book1DTOL = bookDAO.selectAll();
			System.out.println("[BS]도서 전체조회 성공");
			}catch (SQLException e) {
				
			}
			return book1DTOL;
		};

		// 도서정보수정
		public boolean bookEdit(BookDTO bookDTO) throws Exception {
			boolean bookJoin = false;
			try {
				bookJoin = bookDAO.update(bookDTO) > 0;
				System.out.println("[BS]도서수정 성공");
			} catch (SQLException e) {
			}

			return bookJoin;
		};

		// 도서삭제
		public boolean bookDelete(BookDTO bookDTO) throws Exception {
			boolean bookJoin = false;
			try {
				bookJoin = bookDAO.delete(bookDTO) > 0;
				System.out.println("[BS]도서 삭제 성공");
			} catch (SQLException e) {
			}

			return bookJoin;
		}
	
}
